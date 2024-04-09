package vn.edu.iuh.fit.frontend.controllers;

import jakarta.jms.TextMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.iuh.fit.backend.dto.CartListView;
import vn.edu.iuh.fit.backend.dto.LoginForm;
import vn.edu.iuh.fit.backend.dto.ProceedCheckoutOrder;
import vn.edu.iuh.fit.backend.dto.ProductAndQuantityOrdered;
import vn.edu.iuh.fit.backend.models.Customer;
import vn.edu.iuh.fit.backend.models.ProductPrice;
import vn.edu.iuh.fit.backend.repositories.IProductPriceRepository;
import vn.edu.iuh.fit.backend.repositories.IProductRepository;
import vn.edu.iuh.fit.backend.services.impl.CustomerService;
import vn.edu.iuh.fit.backend.utils.Base64EncodingText;
import vn.edu.iuh.fit.backend.utils.JsonUtils;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("")
@AllArgsConstructor
public class ClientController {
    private CustomerService customerService;
    private IProductPriceRepository productPriceRepository;
    private IProductRepository productRepository;
    private Base64EncodingText base64EncodingText;
    private JmsTemplate jmsTemplate;


    @GetMapping("")
    public ModelAndView handleRedirect(ModelAndView modelAndView) {
        return new ModelAndView("redirect:/products");
    }

    @GetMapping("/register")
    public ModelAndView handleOpenRegister(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("account") != null) {
            return new ModelAndView("redirect:/");
        }
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = new Customer();
        modelAndView.addObject("customer", customer);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("customer") Customer customer, Model model) {
        try {
            String newPassword = BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt());
            customer.setPassword(newPassword);
            Customer customerResult = customerService.addCustomer(customer);
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorRegister", "Register failed");
            return "register";
        }
    }

    @GetMapping("/login")
    public ModelAndView handleOpenLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("account") != null) {
            return new ModelAndView("redirect:/");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loginForm", new LoginForm());
        modelAndView.setViewName("login-page");
        return modelAndView;
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("loginForm") LoginForm loginForm, Model model, HttpServletRequest request) {
        Optional<Customer> optionalCustomer = customerService.findCustomerByEmail(loginForm.getUserName());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            if (BCrypt.checkpw(loginForm.getPassword(), customer.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("account", customer);
                return "redirect:/";
            } else {
                model.addAttribute("errorLogin", "Password is incorrect!");
                return "login-page";
            }
        } else {
            model.addAttribute("errorLogin", "Login failed");
            return "login-page";
        }
    }

    @PostMapping("/addToCard")
    public String handleAddToCard(HttpServletRequest request, @RequestParam("quantity") String quantityStr
    , @RequestParam("productID") long productId, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        if (session.getAttribute("account") == null) {
            return "redirect:/login";
        }
        double quantity = 0;
        try {
            quantity = Double.parseDouble(quantityStr);
            if (quantity <= 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("msgAddToCart", "Quantity must be a positive number");
            return "redirect:/products";
        }
        Object carts = session.getAttribute("carts");
        if (carts == null){
            carts = new HashMap<Long, Double>();
        }
        HashMap<Long, Double> cartList = (HashMap<Long, Double>) carts;
        if (cartList.containsKey(productId)){
            cartList.put(productId, cartList.get(productId) + quantity);
        } else {
            cartList.put(productId, quantity);
        }
        session.setAttribute("carts", cartList);
        redirectAttributes.addFlashAttribute("msgAddToCart", "Add to cart successfully");
        return "redirect:/products";
    }
    @GetMapping("/logout")
    public String handleLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }
    @GetMapping("/openCart")
    public ModelAndView handleOpenCart(HttpServletRequest request, ModelAndView modelAndView) {
        HttpSession session = request.getSession();
        Object carts = session.getAttribute("carts");
        double totalPrice = 0;
        if (carts == null || ((HashMap<Long, Double>) carts).size() == 0) {
            return new ModelAndView("redirect:/products");
        }
        HashMap<Long, Double> cartList = (HashMap<Long, Double>) carts;
        List<CartListView> cartListViews = new ArrayList<>();
        for (Long productId : cartList.keySet()) {
            Optional<ProductPrice> newestProductPriceByProductId = productPriceRepository.getNewestProductPriceByProductId(productId);
            ProductPrice productPrice =  newestProductPriceByProductId.orElse(null);
            if(productPrice == null){
                continue;
            } else {
                totalPrice += productPrice.getPrice() * cartList.get(productId);
            }
            CartListView cartListView = new CartListView();
            cartListView.setProduct(productRepository.findById(productId).orElse(null));;
            cartListView.setQuantity(cartList.get(productId));
            cartListView.setNewestPrice(productPrice);

            if (cartListView.getProduct() != null && cartListView.getNewestPrice() != null) {
                cartListViews.add(cartListView);
            }
        }
        modelAndView.addObject("totalPrice", totalPrice);
        modelAndView.addObject("cartListViews", cartListViews);
        modelAndView.setViewName("cart");
        return modelAndView;
    }
    @GetMapping("/deleteCartItem/{productId}")
    public String handleDeleteCartItem(@PathVariable("productId") long productId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object carts = session.getAttribute("carts");
        if (carts != null) {
            HashMap<Long, Double> cartList = (HashMap<Long, Double>) carts;
            cartList.remove(productId);
            session.setAttribute("carts", cartList);
        }
        return "redirect:/openCart";
    }
    @GetMapping("/proceedToCheckout")
    public String handleProceedToCheckout(HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("account") == null) {
            return "redirect:/login";
        }
        Object carts = session.getAttribute("carts");
        if (carts == null || ((HashMap<Long, Double>) carts).size() == 0) {
            return "redirect:/products";
        }

        HashMap<Long, Double> cartList = (HashMap<Long, Double>) carts;

        List<ProductAndQuantityOrdered> productAndQuantityOrdereds = new ArrayList<>();
        for (Long productId : cartList.keySet()) {
            Optional<ProductPrice> newestProductPriceByProductId = productPriceRepository.getNewestProductPriceByProductId(productId);
            ProductPrice productPrice =  newestProductPriceByProductId.orElse(null);
            if(productPrice == null){
                continue;
            }
            ProductAndQuantityOrdered productAndQuantityOrdered = new ProductAndQuantityOrdered();
            productAndQuantityOrdered.setProductId(productId);
            productAndQuantityOrdered.setQuantity(cartList.get(productId));
            productAndQuantityOrdered.setUnitPrice(productPrice.getPrice());


            productAndQuantityOrdereds.add(productAndQuantityOrdered);
        }

        ProceedCheckoutOrder proceedCheckoutOrder = new ProceedCheckoutOrder();
        proceedCheckoutOrder.setCustomerId(((Customer) session.getAttribute("account")).getCustomerId());
        proceedCheckoutOrder.setEmail(((Customer) session.getAttribute("account")).getEmail());
        proceedCheckoutOrder.setProductAndQuantityOrdereds(productAndQuantityOrdereds);

        String json = JsonUtils.convertListProducts2Json(proceedCheckoutOrder);
        String decodeStr = base64EncodingText.encrypt(json);
        jmsTemplate.convertAndSend("order_products", decodeStr);

        redirectAttributes.addFlashAttribute("msgAddToCart", "Proceed to checkout successfully");
        return "redirect:/products";
    }

}
