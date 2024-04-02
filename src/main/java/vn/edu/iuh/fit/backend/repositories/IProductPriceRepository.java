package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.models.ProductPrice;
import vn.edu.iuh.fit.pks.ProductPricePK;

public interface IProductPriceRepository extends JpaRepository<ProductPrice, ProductPricePK> {
}
