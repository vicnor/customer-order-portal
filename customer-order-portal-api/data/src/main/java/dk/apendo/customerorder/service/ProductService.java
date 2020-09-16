package dk.apendo.customerorder.service;

import dk.apendo.customerorder.model.Product;
import dk.apendo.customerorder.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements CrudService<Product, Integer> {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not find product with id " + id));
    }

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not find product with id " + id));
        productRepository.delete(product);
    }
}
