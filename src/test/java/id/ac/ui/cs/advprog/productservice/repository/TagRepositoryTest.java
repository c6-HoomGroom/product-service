package id.ac.ui.cs.advprog.productservice.repository;

import id.ac.ui.cs.advprog.productservice.model.Tag;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TagRepositoryTest {

    @Autowired
    TagRepository tagRepository;
    List<Tag> tags;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tags = new ArrayList<>();
        tags.add(new Tag("Wood"));
        tags.add(new Tag("Metal"));
        tags.add(new Tag("Chair"));
        tags.add(new Tag("Table"));
        tags.add(new Tag("Cutlery"));
    }

    @AfterEach
    void tearDown() {
        tags.clear();
    }

    @Test
    void testSaveAndFind() {
        Tag tag = tags.getFirst();
        Tag result = tagRepository.save(tag);

        Optional<Tag> findResultOptional = tagRepository.findById(tags.getFirst().getId());
        assertTrue(findResultOptional.isPresent()); // Ensure tag is found

        Tag findResult = findResultOptional.get();
        assertEquals(tag.getId(), result.getId());
        assertEquals(tag.getId(), findResult.getId());
        assertEquals(tag.getName(), result.getName());
        assertEquals(tag.getName(), findResult.getName());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Tag tag : tags) {
            tagRepository.save(tag);
        }

        Tag toFind = tags.getLast();
        Optional<Tag> findResultOptional = tagRepository.findById(toFind.getId());
        assertTrue(findResultOptional.isPresent()); // Ensure tag is found

        Tag findResult = findResultOptional.get(); // Extract the Tag from Optional
        assertEquals(toFind.getId(), findResult.getId());
        assertEquals(toFind.getName(), findResult.getName());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Tag tag : tags) {
            tagRepository.save(tag);
        }


        Optional<Tag> findResult = tagRepository.findById(UUID.randomUUID());
        assertTrue(findResult.isEmpty());
    }

    @Test
    void testFindAllTags() {
        for (Tag tag : tags) {
            tagRepository.save(tag);
        }

        List<Tag> tagList = tagRepository.findAll();
        assertEquals(5, tagList.size());
    }

    @Test
    void testDeleteIfExist() {
        Tag tag = tags.getFirst();
        tagRepository.save(tag);
        Optional<Tag> findResult = tagRepository.findById(tag.getId());

        assertNotNull(findResult);
        tagRepository.deleteById(tag.getId());
        findResult = tagRepository.findById(tag.getId());

        assertTrue(findResult.isEmpty());
    }
}
