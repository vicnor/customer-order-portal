package dk.apendo.customerorder.controller;

import dk.apendo.customerorder.model.Product;
import dk.apendo.customerorder.service.ProductService;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("{id}")
    public Product findById(@PathVariable Integer id) {
        return productService.findById(id);
    }

    @PostMapping("")
    public Product add(@RequestBody Product product) {
        return productService.add(product);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {
        productService.deleteById(id);
    }

}
