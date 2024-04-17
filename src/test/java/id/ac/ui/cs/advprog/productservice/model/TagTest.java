package id.ac.ui.cs.advprog.productservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class TagTest {
    Tag tag;

    @BeforeEach
    void setUp() {
        this.tag = new Tag();
        this.tag.setTagName("Perabotan");
    }
    @Test
    void testIdExist() {
        assertNotNull(this.tag.getId());
    }
    @Test
    void testGetTagName() {
        assertEquals("Perabotan", this.tag.getTagName());
    }
}
