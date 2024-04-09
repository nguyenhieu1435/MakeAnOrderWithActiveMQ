package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.backend.pks.ProductPricePK;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_prices")
@IdClass(ProductPricePK.class)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductPrice {
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Id
    @Column(name = "price_date_time")
    private LocalDateTime price_date_time;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "note")
    private String note;

    @Override
    public String toString() {
        return "ProductPrice{" +
                "product=" + product +
                ", price_date_time=" + price_date_time +
                ", price=" + price +
                ", note='" + note + '\'' +
                '}';
    }

    public ProductPrice(LocalDateTime price_date_time, double price, String note) {
        this.price_date_time = price_date_time;
        this.price = price;
        this.note = note;
    }
}
