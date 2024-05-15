package id.ac.ui.cs.advprog.productservice.repository;

import id.ac.ui.cs.advprog.productservice.model.Tag;
import id.ac.ui.cs.advprog.productservice.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
@Rollback
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    TagRepository tagRepository;

    List<Product> productList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Tag wooden = tagRepository.save(new Tag("Wooden"));
        Tag chair = tagRepository.save(new Tag("Chair"));
        Tag metal = tagRepository.save(new Tag("Metal"));
        Tag spoon = tagRepository.save(new Tag("Spoon"));

        Product product = Product.builder()
                .name("Kursi Kayu")
                .tag(wooden)
                .tag(chair)
                .description("Kursi Kayu")
                .image("https://i.ibb.co/KzQjH3p/sam-moghadam-khamseh-kvmds-Tr-GOBM-unsplash.jpg")
                .price(300000)
                .discountPrice(270000)
                .build();

        Product product2 = Product.builder()
                .name("Kursi Metal")
                .tag(metal)
                .tag(chair)
                .description("Kursi Metal")
                .image("https://i.ibb.co/KzQjH3p/sam-moghadam-khamseh-kvmds-Tr-GOBM-unsplash.jpg")
                .price(400000)
                .discountPrice(370000)
                .build();

        Product product3 = Product.builder()
                .name("Sendok Kayu")
                .tag(wooden)
                .tag(spoon)
                .description("Sendok Kayu")
                .image("https://i.ibb.co/KzQjH3p/sam-moghadam-khamseh-kvmds-Tr-GOBM-unsplash.jpg")
                .price(40000)
                .discountPrice(37000)
                .build();

        productList.add(product);
        productList.add(product2);
        productList.add(product3);
    }

    @AfterEach
    void tearDown() {
        productList.clear();
    }

    @Test
    void testSaveCreate() {
        Product product = productList.getFirst();
        Product result = productRepository.save(product);

        Optional<Product> findResultOptional = productRepository.findById(product.getId());
        assertTrue(findResultOptional.isPresent());

        Product findResult = findResultOptional.get();
        assertEquals(product.getId(), result.getId());
        assertEquals(product.getId(), findResult.getId());
        assertEquals(product.getName(), findResult.getName());
        assertEquals(product.getPrice(), findResult.getPrice());
        assertEquals(product.getImage(), findResult.getImage());
        assertEquals(product.getTags(), findResult.getTags());
        assertEquals(product.getDescription(), findResult.getDescription());
        assertEquals(product.getDiscountPrice(), findResult.getDiscountPrice());
    }

    @Test
    void testSaveUpdate() {
        Product product = productList.getFirst();
        productRepository.save(product);
        Product newProduct = Product.builder()
                .id(product.getId())
                .name("New Name")
                .tags(product.getTags())
                .description("New Item")
                .price(product.getPrice())
                .image(product.getImage())
                .discountPrice(product.getDiscountPrice())
                .build();

        Product result = productRepository.save(newProduct);

        Optional<Product> findResultOptional = productRepository.findById(product.getId());
        assertTrue(findResultOptional.isPresent());

        Product findResult = findResultOptional.get();

        assertEquals(product.getId(), newProduct.getId());
        assertEquals(product.getId(), result.getId());
        assertEquals(product.getId(), findResult.getId());
        assertEquals(findResult.getName(), newProduct.getName());
        assertEquals(result.getName(), newProduct.getName());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Product product : productList) {
            productRepository.save(product);
        }

        Product toFind = productList.getLast();
        Optional<Product> findResultOptional = productRepository.findById(toFind.getId());
        assertTrue(findResultOptional.isPresent());

        Product findResult = findResultOptional.get();
        assertEquals(toFind.getId(), findResult.getId());
        assertEquals(toFind.getName(), findResult.getName());
        assertEquals(toFind.getPrice(), findResult.getPrice());
        assertEquals(toFind.getImage(), findResult.getImage());
        assertEquals(toFind.getTags(), findResult.getTags());
        assertEquals(toFind.getDescription(), findResult.getDescription());
        assertEquals(toFind.getDiscountPrice(), findResult.getDiscountPrice());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Product product : productList) {
            productRepository.save(product);
        }

        Optional<Product> findResultOptional = productRepository.findById(UUID.randomUUID());
        assertTrue(findResultOptional.isEmpty());
    }

    @Test
    void testFindAllProducts() {
        for (Product product : productList) {
            productRepository.save(product);
        }

        List<Product> products = productRepository.findAll();
        assertEquals(3, products.size());
    }

    @Test
    void testDeleteIfExist() {
        Product product = productList.get(1);
        productRepository.save(product);
        Optional<Product> findResultOptional = productRepository.findById(product.getId());
        Product findResult = findResultOptional.get();

        assertNotNull(findResult);
        productRepository.deleteById(product.getId());
        findResultOptional = productRepository.findById(product.getId());
        assertTrue(findResultOptional.isEmpty());
    }
}
