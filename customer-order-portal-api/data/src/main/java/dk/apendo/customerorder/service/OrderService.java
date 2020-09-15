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
    public Order findById(Integer integer) {
        return null;
    }

    @Override
    public Order add(Order object) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

}
