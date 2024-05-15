package id.ac.ui.cs.advprog.productservice.service;

import id.ac.ui.cs.advprog.productservice.model.Product;
import id.ac.ui.cs.advprog.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(UUID.fromString(id));
    }

//    @Override
//    public void delete(String id) {
//        productRepository.deleteById(id);
//    }

    @Override
    public Product findById(String id) {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        return optionalProduct.orElse(null);
    }

//    @Override
//    public Product findById(String id) {
//        Optional<Product> optionalProduct = productRepository.findById(id);
//        return optionalProduct.orElse(null);
//    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
