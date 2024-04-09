package vn.edu.iuh.fit.backend.dto;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@ToString
public class ProceedCheckoutOrder {
    private long customerId;
    private List<ProductAndQuantityOrdered> productAndQuantityOrdereds;
    private String email;

}
