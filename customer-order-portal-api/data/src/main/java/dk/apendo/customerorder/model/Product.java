package dk.apendo.customerorder.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTS")
public class Product extends BaseEntity {

    @Column(name = "NAME")
    private String name;

}
