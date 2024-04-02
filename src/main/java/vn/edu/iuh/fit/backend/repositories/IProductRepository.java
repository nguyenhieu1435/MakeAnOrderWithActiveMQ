package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.models.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
