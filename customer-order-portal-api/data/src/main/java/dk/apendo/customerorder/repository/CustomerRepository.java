package dk.apendo.customerorder.repository;

import dk.apendo.customerorder.repository.ORM.CustomerORM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerORM, Integer> {
}
