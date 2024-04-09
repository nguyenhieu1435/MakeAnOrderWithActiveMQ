package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.backend.enums.ProductStatus;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long productId;
    @Column(name = "name", length = 150, nullable = false)
    private String name;
    @Lob
    @Column(name = "description",  columnDefinition = "text", nullable = false)
    private String description;
    @Column(name = "unit", length = 25, nullable = false)
    private String unit;
    @Column(name = "manufacturer_name", length = 100, nullable = false)
    private String manufacturer;
    @Column(name = "status")
    private ProductStatus status;
    @Column(name = "image")
    private String image;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductPrice> productPrices;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private ProductQuantity quantity;

    public Product(long productId) {
        this.productId = productId;
    }

    public Product(String name, String description, String unit, String manufacturer, ProductStatus status, String image) {
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.manufacturer = manufacturer;
        this.status = status;
        this.image = image;

    }
}
