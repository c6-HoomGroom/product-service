package id.ac.ui.cs.advprog.productservice.service;
import id.ac.ui.cs.advprog.productservice.model.Tag;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {
    public Tag create(Tag tag);
    public void delete(String id);
    public Tag findById(String id);
    public Tag findByName(String name);
    public List<Tag> findAll();
}