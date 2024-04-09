package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.iuh.fit.backend.models.ProductPrice;
import vn.edu.iuh.fit.backend.pks.ProductPricePK;

import java.util.Optional;

public interface IProductPriceRepository extends JpaRepository<ProductPrice, ProductPricePK> {
    @Query("select pp from ProductPrice pp where pp.product.productId = :productID order by pp.price_date_time desc limit 1")
    public Optional<ProductPrice> getNewestProductPriceByProductId(long productID);
}
