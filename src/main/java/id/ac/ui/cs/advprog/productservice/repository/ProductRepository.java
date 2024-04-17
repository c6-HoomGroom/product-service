package id.ac.ui.cs.advprog.productservice.repository;

import id.ac.ui.cs.advprog.productservice.model.Product;
import id.ac.ui.cs.advprog.productservice.model.Tag;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product save(Product product) {
        productData.add(product);
        return product;
    }

    public Product update(Product product) {
        int i = 0;
        for (Product savedProduct:productData) {
            if (savedProduct.getId().equals(product.getId())) {
                productData.remove(i);
                productData.add(i, product);
                return product;
            }
            i+=1;
        }
        return null;
    }

    public void delete(UUID id) throws RuntimeException {
        Product deletedProduct = findById(id);
        if (deletedProduct == null) {
            throw new RuntimeException("Tag not found for id: " + id.toString());
        }
        productData.remove(deletedProduct);
    }

    public Product findById(UUID id) {
        for (Product savedProduct : productData) {
            if (savedProduct.getId().equals(id)) {
                return savedProduct;
            }
        }
        return null;
    }

    public List<Product> findAll() {
        return new ArrayList<>(productData);
    }
}
