package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.pks.OrderDetailPK;

@Entity
@Table(name = "order_details")
@IdClass(OrderDetailPK.class)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private double quantity;
    private double price;


    @Override
    public String toString() {
        return "OrderDetail{" +
                "product=" + product +
                ", order=" + order +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
