package id.ac.ui.cs.advprog.productservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    Product product;
    Set<String> productTags = new HashSet<>();

    @BeforeEach
    void setUp() {
        this.product = Product.builder()
                .name("Kursi Kayu")
                .tag(new Tag("Wooden"))
                .tag(new Tag("Chair"))
                .description("Produk ini adalah kursi kayu sederhana yang cocok untuk ruangan mana saja.")
                .image("https://i.ibb.co/KzQjH3p/sam-moghadam-khamseh-kvmds-Tr-GOBM-unsplash.jpg")
                .price(300000)
                .discountPrice(270000)
                .build();
    }

    @Test
    void testIdExist() {
        assertNotNull(this.product.getId());
    }
    @Test
    void testChangeId() {
        this.product.setId(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.product.getId().toString());
    }
    @Test
    void testGetName() {
        assertEquals(this.product.getName(), "Kursi Kayu");
    }
    @Test
    void testGetTags() {
        this.productTags.add("Wooden");
        this.productTags.add("Chair");

        Set<String> tagNames = this.product.getTags().stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());

        assertEquals(tagNames, this.productTags);
    }
    @Test
    void testGetDescription() {
        assertEquals(this.product.getDescription(),
                "Produk ini adalah kursi kayu sederhana yang cocok untuk ruangan mana saja.");
    }
    @Test
    void testGetImage() {
        assertEquals(this.product.getImage(),
                "https://i.ibb.co/KzQjH3p/sam-moghadam-khamseh-kvmds-Tr-GOBM-unsplash.jpg");
    }
    @Test
    void testGetPrice() {
        assertEquals(this.product.getPrice(), 300000);
    }
    @Test
    void testGetDiscountPrice() {
        assertEquals(this.product.getDiscountPrice(), 270000);
    }
}
