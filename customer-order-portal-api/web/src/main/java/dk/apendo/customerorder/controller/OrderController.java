package dk.apendo.customerorder.controller;

import dk.apendo.customerorder.model.Order;
import dk.apendo.customerorder.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("{id}")
    public Order findBy(@PathVariable Integer id) {
        return orderService.findById(id);
    }

    @PostMapping("")
    public Order add(@RequestBody Order order) {
        return orderService.add(order);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {
        orderService.deleteById(id);
    }
}
