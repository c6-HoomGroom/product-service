package id.ac.ui.cs.advprog.productservice.service;

import id.ac.ui.cs.advprog.productservice.model.Tag;
import id.ac.ui.cs.advprog.productservice.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag create(Tag tag) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Tag findById(String id) {
        return null;
    }

    @Override
    public Tag findByName(String name) {
        return null;
    }

    @Override
    public List<Tag> findAll() {
        return null;
    }
}
