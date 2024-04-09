package vn.edu.iuh.fit.backend.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@ToString
public class ProductAndQuantityOrdered {
    private long productId;
    private double quantity;
    private double unitPrice;
}
