package id.ac.ui.cs.advprog.productservice.controller;

import id.ac.ui.cs.advprog.productservice.model.Product;
import id.ac.ui.cs.advprog.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController
{
    @Autowired
    private ProductService productService;

    @GetMapping("/api/id/{id}")
    public Product getProductById(@PathVariable String id) {
        Product product = productService.findById(id);
        return product;
    }

    @GetMapping("/api")
    public List<Product> getAllProduct() {
        return productService.findAll();
    }
}
