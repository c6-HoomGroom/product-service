package id.ac.ui.cs.advprog.productservice.repository;

import id.ac.ui.cs.advprog.productservice.model.Tag;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TagRepository {
    private Set<String> tagNames = new HashSet<>();
    private List<Tag> tagData = new ArrayList<>();

    public Tag save(Tag tag) {
        if (!tagNames.add(tag.getName())) {
            return findByName(tag.getName());
        }

        tagData.add(tag);
        return tag;
    }

    public void delete(UUID id) throws TagNotFoundException {
        Tag deletedTag = findById(id);
        if (deletedTag == null) {
            throw new TagNotFoundException("Tag not found for id: " + id.toString());
        }
        tagData.remove(deletedTag);
    }

    public Tag findById(UUID id) {
        for (Tag savedTag : tagData) {
            if (savedTag.getId().equals(id)) {
                return savedTag;
            }
        }

        return null;
    }

    public Tag findByName(String name) {
        for (Tag savedTag : tagData) {
            if (savedTag.getName().equals(name)) {
                return savedTag;
            }
        }

        return null;
    }

    public List<Tag> findAll() {
        return new ArrayList<>(tagData);
    }

    public static class TagNotFoundException extends RuntimeException {
        public TagNotFoundException(String message) {
            super(message);
        }
    }
}
