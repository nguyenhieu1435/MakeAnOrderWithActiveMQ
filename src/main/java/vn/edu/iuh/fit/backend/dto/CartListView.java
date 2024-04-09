package vn.edu.iuh.fit.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.backend.models.ProductPrice;
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class CartListView {
    private Product product;
    private double quantity;
    private ProductPrice newestPrice;
}
