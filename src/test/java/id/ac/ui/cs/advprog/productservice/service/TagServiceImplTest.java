package id.ac.ui.cs.advprog.productservice.service;
import id.ac.ui.cs.advprog.productservice.model.Tag;

import id.ac.ui.cs.advprog.productservice.repository.TagRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class TagServiceImplTest {

    @InjectMocks
    TagServiceImpl tagService;

    @Mock
    TagRepository tagRepository;

    List<Tag> tags;

    @BeforeEach
    void setUp() {
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

    Tag mockFindById(UUID id) {
        for (Tag savedTag : tags) {
            if (savedTag.getId().equals(id)) {
                return savedTag;
            }
        }

        return null;
    }

    Tag mockFindByName(String name) {
        for (Tag savedTag : tags) {
            if (savedTag.getName().equals(name)) {
                return savedTag;
            }
        }

        return null;
    }

    @Test
    void testCreate() {
        Tag tag = tags.get(0);
        when(tagRepository.save(tag)).thenReturn(tag);

        Tag createdTag = tagService.create(tag);
        assertNotNull(createdTag);
        assertEquals(tag.getName(), createdTag.getName());

        verify(tagRepository, times(1)).save(tag);

    }
    @Test
    void testDelete() {
        Tag tag = tags.get(1);
        doAnswer(invocation -> {
            tags.remove(tag);
            return null;
        }).when(tagRepository).delete(tag.getId());

        when(tagRepository.findAll()).thenReturn(tags);

        List<Tag> current = tagService.findAll();
        assertEquals(5, current.size());

        tagService.delete(tag.getId().toString());

        List<Tag> afterDelete = tagService.findAll();
        assertEquals(4, afterDelete.size());

        verify(tagRepository, times(1)).delete(tag.getId());
    }
    @Test
    void testDeleteIfNotFound() {
        UUID tagId = UUID.fromString("0");
        doAnswer(invocation -> {
            throw new RuntimeException("Tag not found for id: " + tagId.toString());
        }).when(tagRepository).delete(tagId);

        assertThrows(RuntimeException.class, () -> tagService.delete(tagId.toString()));

        verify(tagRepository, times(1)).delete(tagId);
    }
    @Test
    void testFindAll() {
        when(tagRepository.findAll()).thenReturn(tags);

        List<Tag> allTags = tagService.findAll();

        assertEquals(tags.size(), allTags.size());

        verify(tagRepository, times(1)).findAll();
    }
    @Test
    void testFindById() {
        Tag tag = tags.get(3);
        UUID tagId = tag.getId();
        when(tagRepository.findById(tagId)).thenReturn(mockFindById(tagId));
        Tag foundTag = tagService.findById(tagId.toString());

        assertNotNull(foundTag);
        assertEquals(foundTag.getId(), tag.getId());
        assertEquals(foundTag.getName(), tag.getName());

        verify(tagRepository, times(1)).findById(tagId);
    }
    @Test
    void testFindByName() {
        Tag tag = tags.get(3);
        String tagName = tag.getName();
        when(tagRepository.findByName(tagName)).thenReturn(mockFindByName(tagName));
        Tag foundTag = tagService.findByName(tagName.toString());

        assertNotNull(foundTag);
        assertEquals(foundTag.getId(), tag.getId());
        assertEquals(foundTag.getName(), tag.getName());

        verify(tagRepository, times(1)).findByName(tagName);
    }
}
