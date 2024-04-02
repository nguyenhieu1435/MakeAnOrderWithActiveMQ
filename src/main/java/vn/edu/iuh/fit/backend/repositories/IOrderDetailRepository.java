package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.models.OrderDetail;
import vn.edu.iuh.fit.pks.OrderDetailPK;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {
}
