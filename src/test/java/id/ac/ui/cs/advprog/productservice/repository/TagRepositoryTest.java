package id.ac.ui.cs.advprog.productservice.repository;

import id.ac.ui.cs.advprog.productservice.model.Tag;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Equality;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TagRepositoryTest {
    TagRepository tagRepository;
    List<Tag> tags;

    @BeforeEach
    void setUp() {
        tagRepository = new TagRepository();

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

        Tag findResult = tagRepository.findById(tags.getFirst().getId());
        assertEquals(tag.getId(), result.getId());
        assertEquals(tag.getId(), findResult.getId());
        assertEquals(tag.getName(), result.getName());
        assertEquals(tag.getName(), findResult.getName());
    }

    @Test
    void testSaveDuplicate() {
        Tag tag = tags.getFirst();
        Tag tag2 = new Tag(tag.getName());
        Tag result = tagRepository.save(tag);
        Tag result2 = tagRepository.save(tag2);

        assertEquals(result.getId(), result2.getId());
        assertEquals(result.getName(), result2.getName());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Tag tag : tags) {
            tagRepository.save(tag);
        }

        Tag toFind = tags.getLast();
        Tag findResult = tagRepository.findById(toFind.getId());
        assertEquals(toFind.getId(), findResult.getId());
        assertEquals(toFind.getName(), findResult.getName());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Tag tag : tags) {
            tagRepository.save(tag);
        }

        Tag findResult = tagRepository.findById("Alice");
        assertNull(findResult);
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
        Tag findResult = tagRepository.findById(tag.getId());

        assertNotNull(findResult);
        tagRepository.delete(tag.getId());
        findResult = tagRepository.findById(tag.getId());

        assertNull(findResult);
    }

    @Test
    void testDeleteIfNotExist() {
        Tag tag = tags.getFirst();
        tagRepository.save(tag);
        Tag findResult = tagRepository.findById(tag.getId());

        assertNotNull(findResult);

        assertThrows(RuntimeException.class, () -> tagRepository.delete("0"));
    }
}
