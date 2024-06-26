package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backend.models.Order;

public interface IOrderRepository extends JpaRepository<Order, Long> {
}
