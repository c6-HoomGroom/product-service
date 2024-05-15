package id.ac.ui.cs.advprog.productservice.repository;

import id.ac.ui.cs.advprog.productservice.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}