package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.models.Order;

public interface IOrderRepository extends JpaRepository<Order, Long> {
}
