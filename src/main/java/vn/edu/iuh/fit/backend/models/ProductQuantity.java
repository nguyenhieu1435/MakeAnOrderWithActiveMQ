package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_quantity")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductQuantity {
    @Id
    @Column(name = "product_id")
    private long productId;
    private double quantity;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;

}
