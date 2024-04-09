package vn.edu.iuh.fit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.edu.iuh.fit.backend.enums.ProductStatus;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.repositories.*;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MakeAnOrderWithActiveMqApplication implements CommandLineRunner {
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IProductPriceRepository productPriceRepository;
    @Autowired
    private IProductQuantityRepository productQuantityRepository;
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    public static void main(String[] args) {
        SpringApplication.run(MakeAnOrderWithActiveMqApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
         Product product = new Product("Táo Envy Mỹ size 100",
                 "Táo Envy Mỹ size 100 là một loại táo có độ ngọt và giòn nhất thế giới. Táo Envy Mỹ size 100 tuy quả nhỏ nhưng thịt chắc, giòn, mọng nước và có mức giá bán phải chăng. Nếu bạn có nhu cầu mua để ăn hàng ngày, làm sinh tố, bánh ngọt thì đây là một lựa chọn rất thích hợp. Đến với Homefarm để mua sắm ngay thôi nào.",
                 "kg",
                 "Homefarm",
                 ProductStatus.ACTIVE,
                 "https://product.hstatic.net/1000301274/product/_10101065__tao_envy_my_size_100__1__c186b7dbf078497db0c5c47ef4228e03.jpg"
         );
         ProductPrice productPrice = new ProductPrice(LocalDateTime.now(), 94500, "Giá sale");
         productPrice.setProduct(product);
         ProductQuantity productQuantity = new ProductQuantity();
         productQuantity.setProduct(product);
         productQuantity.setQuantity(2000);
         product.setQuantity(productQuantity);
         product.setProductPrices(List.of(productPrice));

         product = productRepository.save(product);


            Product product2 = new Product("Ba Chỉ Bò Nhập Khẩu Nướng Short Plate khay"
                    , "Ba chỉ bò nhập khẩu nướng của Homefarm, là sự kết hợp tinh tế giữa thịt nạc và thịt mỡ từ phần bụng của con bò. Được bào lát mỏng 1,5 mm, đây không chỉ là cách lý tưởng để nhúng lẩu mà còn tạo ra độ mềm và vị ngọt đặc trưng, đảm bảo sự hài lòng khi thưởng thức. Điều này giúp sản phẩm của Homefarm luôn là sự lựa chọn hàng đầu trong lòng khách hàng, không chỉ vì hương vị tuyệt vời mà còn vì sự tinh tế trong cách chế biến."
                    , "kg", "Excel Cargill", ProductStatus.ACTIVE, "https://product.hstatic.net/1000301274/product/ba_chi_nuong_a8060396a4f04875badb398c4df732cd.png");
            ProductPrice productPrice2 = new ProductPrice(LocalDateTime.now(), 115000, "Ra mắt");
            productPrice2.setProduct(product2);
            ProductQuantity productQuantity2 = new ProductQuantity();
            productQuantity2.setProduct(product2);
            productQuantity2.setQuantity(15);
            product2.setQuantity(productQuantity2);
            product2.setProductPrices(List.of(productPrice2));

            product2 = productRepository.save(product2);

            Product product3 = new Product("Bánh bông lan Panettone classic trái cây Bauducco"
                    , "Bánh bông lan Panettone classic trái cây Bauducco 100g món bánh ngọt lừng danh có nguồn gốc từ thành phố Milan nước Ý hiện đã có mặt tại Homefarm để phục vụ nhu cầu mua sắm của các gia đình Việt. Sản phẩm được đóng hộp nhỏ gọn và có thể thưởng thức ngay hương vị thơm ngon khi vừa mở bao bì."
                    , "Hộp", "Bauducco", ProductStatus.ACTIVE, "https://product.hstatic.net/1000301274/product/_10100958__banh_bong_lan_panettone_classic_trai_cay_bauducco_100g_b7a1bfdf393b460fbfdbd3d310c86170_grande.png");
            ProductPrice productPrice3 = new ProductPrice(LocalDateTime.now(), 36000, "Ra mắt");
            productPrice3.setProduct(product3);
            ProductQuantity productQuantity3 = new ProductQuantity();
            productQuantity3.setProduct(product3);
            productQuantity3.setQuantity(25);
            product3.setQuantity(productQuantity3);
            product3.setProductPrices(List.of(productPrice3));

            product3 = productRepository.save(product3);

        Product product4 = new Product("Bánh gạo Topokki vị phomai 120g"
                , "Bánh gạo Topokki vị phomai 120g món ăn vặt nổi tiếng và được yêu thích của Hàn Quốc này đã có mặt tại cửa hàng của Homefarm. Sản phẩm có thiết kế đóng hộp tiện lợi, có thể mang bên người trong những chuyến du lịch, picnic. Sản phẩm luôn nằm trong danh sách cháy hàng nhờ hương vị béo ngậy, hấp dẫn."
                , "Cốc", "Yopokki ", ProductStatus.ACTIVE, "https://product.hstatic.net/1000301274/product/10100017-banh_gao_topokki_vi_phomai_120g_-coc_a1124759b87140b49a05a6208b819e0d_grande.png");
        ProductPrice productPrice4 = new ProductPrice(LocalDateTime.now(), 55000, "Ra mắt");
        productPrice4.setProduct(product4);
        ProductQuantity productQuantity4 = new ProductQuantity();
        productQuantity4.setProduct(product4);
        productQuantity4.setQuantity(10);
        product4.setQuantity(productQuantity4);
        product4.setProductPrices(List.of(productPrice4));

        product4 = productRepository.save(product4);

        Product product5 = new Product("Bánh mì tươi đông lạnh O'smile 350g"
                , "Bánh mì tươi đông lạnh O'smile 350g được sản xuất với tiêu chí ngon, sạch, tiện lợi và bền vững. Sản phẩm được đóng gói tiện lợi giúp bạn nấu nướng tiện lợi, an tâm về chất lượng sản phẩm. Không cần đi đâu xa, nấu nướng ngay tại gia mà vẫn chuẩn hương vị bánh mì Việt Nam. Sản phẩm hiện có mặt tại hệ thống cửa hàng Homefarm khu vực Hồ Chí Minh."
                , "Bao", "O'smile ", ProductStatus.ACTIVE, "https://product.hstatic.net/1000301274/product/_10101031__banh_mi_tuoi_dong_lanh_o_smile_350g_t10-1_5ca0e5d734bd4e02bdbacc2fe1d5e04f_grande.png");
        ProductPrice productPrice5 = new ProductPrice(LocalDateTime.now(), 37000, "Ra mắt");
        productPrice5.setProduct(product5);
        ProductQuantity productQuantity5 = new ProductQuantity();
        productQuantity5.setProduct(product5);
        productQuantity5.setQuantity(12);
        product5.setQuantity(productQuantity5);
        product5.setProductPrices(List.of(productPrice5));

        product5 = productRepository.save(product5);

        Product product6 = new Product("Bia Budweiser Sleek lon 330ml"
                , "Là thương hiệu bia Mỹ đứng đầu thế giới, Budweiser được ủ từ men bia và mạch nha đặc trưng của Hoa Kỳ và các nước châu Âu. "
                , "Lon", "Việt Nam", ProductStatus.ACTIVE, "https://product.hstatic.net/1000301274/product/10100742-bia_budweiser_sleek_lon_330ml_9e77c11343164ffe8cee117bfdf431b7_grande.png");
        ProductPrice productPrice6 = new ProductPrice(LocalDateTime.now(), 23000, "Ra mắt");
        productPrice6.setProduct(product6);
        ProductQuantity productQuantity6 = new ProductQuantity();
        productQuantity6.setProduct(product6);
        productQuantity6.setQuantity(16);
        product6.setQuantity(productQuantity6);
        product6.setProductPrices(List.of(productPrice6));

        product6 = productRepository.save(product6);

        Product product7 = new Product("Bia Corona Extra chai 330ml"
                , "Bia Corona Extra chai 330ml là loại bia đặc trưng từ Mexico với lịch sử gần 100 năm có mặt trên thị trường. Bia Corona Extra chai 330ml được ưa chuộng bởi vị bia dịu êm, không gắt, không đắng, thích hợp với mọi cuộc vui"
                , "Chai", "Mexico", ProductStatus.ACTIVE, "https://product.hstatic.net/1000301274/product/10100744-bia_corona_extra_chai_330ml_942ee5ab23864eb3b36c23c4642953e3_grande.png");
        ProductPrice productPrice7 = new ProductPrice(LocalDateTime.now(), 45000, "Ra mắt");
        productPrice7.setProduct(product7);
        ProductQuantity productQuantity7 = new ProductQuantity();
        productQuantity7.setProduct(product7);
        productQuantity7.setQuantity(122);
        product7.setQuantity(productQuantity7);
        product7.setProductPrices(List.of(productPrice7));

        product7 = productRepository.save(product7);

        Product product8 = new Product("Bò viên Homecookl"
                , "Bò viên Homecook gây ấn tượng với biết bao khách hàng của Homefarm nhờ hương vị thơm ngon, đa dạng trọng cách chế biến. Bạn có thể sử dụng bò viên cho nhiều món ăn gia đình hàng ngày hoặc dùng với các món bún, phở truyền thống. Đặc biệt, Homefarm luôn mang tới khách hàng sản phẩm chất lượng cao với mức giá phải chăng nhất trên thị trường."
                , "Túi", "Homecook", ProductStatus.ACTIVE, "https://product.hstatic.net/1000301274/product/_10100736_-_bo_vien_homecook_0fce0d1ef0c34ff7a99df0e2b7c2cc8d_grande.png");
        ProductPrice productPrice8 = new ProductPrice(LocalDateTime.now(), 45000, "Ra mắt");
        productPrice8.setProduct(product8);
        ProductQuantity productQuantity8 = new ProductQuantity();
        productQuantity8.setProduct(product8);
        productQuantity8.setQuantity(45);
        product8.setQuantity(productQuantity8);
        product8.setProductPrices(List.of(productPrice8));

        product8 = productRepository.save(product8);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        Order order = new Order();
//        order.setOrderDate(LocalDateTime.now());
//        order.setCustomer(new Customer(1L));
//        order = orderRepository.save(order);
//
//        Product product = productRepository.findById(1l).orElse(null);
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setOrder(order);
//        orderDetail.setPrice(22222);
//        orderDetail.setQuantity(2);
//        orderDetail.setProduct(product);
//        orderDetail.setOrder(order);
//        orderDetail = orderDetailRepository.save(orderDetail);
//    }
}
