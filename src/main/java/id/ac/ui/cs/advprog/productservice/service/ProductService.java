package id.ac.ui.cs.advprog.productservice.service;
import id.ac.ui.cs.advprog.productservice.model.Product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public Product create(Product product);
    public Product update(Product product);
    public void delete(String id);
    public Product findById(String id);
    public List<Product> findAll();
}
