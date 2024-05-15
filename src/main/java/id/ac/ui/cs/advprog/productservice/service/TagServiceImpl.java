package id.ac.ui.cs.advprog.productservice.service;

import id.ac.ui.cs.advprog.productservice.model.Tag;
import id.ac.ui.cs.advprog.productservice.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag create(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void delete(String id) {
        tagRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public Tag findById(String id) {
        Optional<Tag> optionalTag = tagRepository.findById(UUID.fromString(id));
        return optionalTag.orElse(null);
    }

//    @Override
//    public void delete(String id) {
//        tagRepository.deleteById(id);
//    }
//
//    @Override
//    public Tag findById(String id) {
//        Optional<Tag> optionalTag = tagRepository.findById(id);
//        return optionalTag.orElse(null);
//    }

    @Override
    public Tag findByName(String name) {
        Optional<Tag> optionalTag = tagRepository.findByName(name);
        return optionalTag.orElse(null);
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }
}
