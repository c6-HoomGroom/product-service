package id.ac.ui.cs.advprog.productservice.repository;

import id.ac.ui.cs.advprog.productservice.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {
    Optional<Tag> findByName(String name);
}
