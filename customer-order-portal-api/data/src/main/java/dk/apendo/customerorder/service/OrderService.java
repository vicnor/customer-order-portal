package dk.apendo.customerorder.service;

import dk.apendo.customerorder.model.Order;
import dk.apendo.customerorder.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements CrudService<Order, Integer> {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not find order with id " + id));
    }

    @Override
    public Order add(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not find order with id " + id));
        orderRepository.delete(order);
    }

}
