package vn.edu.iuh.fit.backend.dto;

import lombok.*;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.backend.models.ProductPrice;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductShowing {
    private Product product;
    private ProductPrice productPrice;
}
