package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long order_id;
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;
    @Column(name = "employee_name", nullable = false)
    private String employeeName;
    @Column(name = "customer_name", nullable = false)
    private String customerName;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;


    public Order(LocalDateTime orderDate, String employeeName, String customerName) {
        this.orderDate = orderDate;
        this.employeeName = employeeName;
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderDate=" + orderDate +
                ", employeeName='" + employeeName + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
