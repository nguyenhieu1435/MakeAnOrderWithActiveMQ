package vn.edu.iuh.fit.backend.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backend.models.ProductQuantity;

public interface IProductQuantityRepository extends JpaRepository<ProductQuantity, Long> {
}
