package vn.edu.iuh.fit.frontend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.backend.repositories.IOrderRepository;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private IOrderRepository orderRepository;

}
