package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Customer {
    @Id
    @Column(name = "cust_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;
    @Column(name = "email", columnDefinition = "varchar(100)", nullable = false, unique = true)
    private String email;
    @Column(name = "cust_name", columnDefinition = "varchar(100)", nullable = false)
    private String customerName;
    @Column(name = "dob")
    private LocalDateTime dateOrBirth;
    @Column(name = "phone", columnDefinition = "varchar(12)", nullable = false)
    private String phoneNumber;
    @Column(name = "password", columnDefinition = "varchar(100)", nullable = false)
    private String password;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    public Customer(long customerId) {
        this.customerId = customerId;
    }

    public Customer(String email, String customerName, LocalDateTime dateOrBirth, String phoneNumber, String password) {
        this.email = email;
        this.customerName = customerName;
        this.dateOrBirth = dateOrBirth;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", email='" + email + '\'' +
                ", customerName='" + customerName + '\'' +
                ", dateOrBirth=" + dateOrBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password +
                '}';
    }
}
