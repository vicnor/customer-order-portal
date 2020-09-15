package dk.apendo.customerorder.repository;

import dk.apendo.customerorder.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
