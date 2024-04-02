package vn.edu.iuh.fit.backend.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backend.models.ProductQuantity;
import vn.edu.iuh.fit.backend.pks.ProductPricePK;

public interface IProductQuantity extends JpaRepository<ProductQuantity, Long> {
}
