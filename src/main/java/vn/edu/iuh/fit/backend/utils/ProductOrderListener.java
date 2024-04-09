package vn.edu.iuh.fit.backend.utils;
import com.google.gson.Gson;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.backend.dto.CartListView;
import vn.edu.iuh.fit.backend.dto.ProceedCheckoutOrder;
import vn.edu.iuh.fit.backend.dto.ProductAndQuantityOrdered;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.repositories.IOrderDetailRepository;
import vn.edu.iuh.fit.backend.repositories.IOrderRepository;
import vn.edu.iuh.fit.backend.repositories.IProductPriceRepository;
import vn.edu.iuh.fit.backend.repositories.IProductRepository;
import vn.edu.iuh.fit.backend.services.ICustomerService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Component
@AllArgsConstructor
public class ProductOrderListener {
    private Base64EncodingText base64EncodingText;
    private IProductRepository productRepository;
    private IOrderRepository orderRepository;
    private IOrderDetailRepository orderDetailRepository;
    @Autowired
    private JavaMailSender mailSender;
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        System.out.println(to);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    private boolean isQuantityGreaterThanAvailable(List<ProductAndQuantityOrdered> productAndQuantityOrdereds) {
        boolean check = false;
        for (ProductAndQuantityOrdered productAndQuantityOrdered : productAndQuantityOrdereds) {
            double quantity = productAndQuantityOrdered.getQuantity();
            Optional<Product> productOptional = productRepository.findById(productAndQuantityOrdered.getProductId());
            if (productOptional.isPresent()) {
                Product product = productOptional.get();

                if (product.getQuantity().getQuantity() < quantity) {
                    check = true;
                    break;
                }
            } else {
                check = true;
                break;
            }
        }
        return check;
    }
    @JmsListener(destination = "order_products")
    public void receiveMessage(final Message jsonMessage) throws Exception {
        String messageData = null;
        String response = null;
        if (jsonMessage instanceof TextMessage){
            String encodeStr = ((TextMessage) jsonMessage).getText();
            String jsonData = base64EncodingText.decrypt(encodeStr);
            ProceedCheckoutOrder proceedCheckoutOrder = JsonUtils.convertJson2ListProducts(jsonData);
            boolean check = isQuantityGreaterThanAvailable(proceedCheckoutOrder.getProductAndQuantityOrdereds());
            if (!check) {
                Order order = new Order();
                order.setOrderDate(LocalDateTime.now());
                order.setCustomer(new Customer(proceedCheckoutOrder.getCustomerId()));
                order = orderRepository.save(order);


                for (ProductAndQuantityOrdered productAndQuantityOrdered : proceedCheckoutOrder.getProductAndQuantityOrdereds()) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setPrice(productAndQuantityOrdered.getUnitPrice() * productAndQuantityOrdered.getQuantity());
                    orderDetail.setQuantity(productAndQuantityOrdered.getQuantity());
                    orderDetail.setProduct(productRepository
                            .findById(productAndQuantityOrdered.getProductId()).get());
                    orderDetail.setOrder(order);
                    orderDetailRepository.save(orderDetail);
                }
                sendEmail(proceedCheckoutOrder.getEmail(), "Order Confirmation", "Your order has been confirmed");
            } else {
                sendEmail(proceedCheckoutOrder.getEmail(), "Order Confirmation", "Your order has been rejected");
                System.out.println("error when quantity > available quantity");
            }
        }
    }
}
