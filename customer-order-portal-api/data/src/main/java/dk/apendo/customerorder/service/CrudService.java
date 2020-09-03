package dk.apendo.customerorder.service;

import java.util.List;

public interface CrudService<T, ID> {

    List<T> findAll();

    T findById(ID id);

    T add(T object);

    void deleteById(ID id);
}
