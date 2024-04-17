package id.ac.ui.cs.advprog.productservice.repository;

import id.ac.ui.cs.advprog.productservice.model.Tag;
import id.ac.ui.cs.advprog.productservice.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    List<Product> productList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Product product = Product.builder()
                .name("Kursi Kayu")
                .tag(new Tag("Wooden"))
                .tag(new Tag("Chair"))
                .description("Kursi Kayu")
                .image("https://i.ibb.co/KzQjH3p/sam-moghadam-khamseh-kvmds-Tr-GOBM-unsplash.jpg")
                .price(300000)
                .discountPrice(270000)
                .build();

        Product product2 = Product.builder()
                .name("Kursi Metal")
                .tag(new Tag("Metal"))
                .tag(new Tag("Chair"))
                .description("Kursi Metal")
                .image("https://i.ibb.co/KzQjH3p/sam-moghadam-khamseh-kvmds-Tr-GOBM-unsplash.jpg")
                .price(400000)
                .discountPrice(370000)
                .build();

        Product product3 = Product.builder()
                .name("Sendok Kayu")
                .tag(new Tag("Wooden"))
                .tag(new Tag("Spoon"))
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

        Product findResult = productRepository.findById(product.getId());
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

        Product result = productRepository.update(newProduct);

        Product findResult = productRepository.findById(product.getId());

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
        Product findResult = productRepository.findById(toFind.getId());
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

        Product findResult = productRepository.findById(UUID.randomUUID());
        assertNull(findResult);
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
        Product result = productRepository.save(product);
        Product findResult = productRepository.findById(product.getId());

        assertNotNull(findResult);
        productRepository.delete(product.getId());
        findResult = productRepository.findById(product.getId());

        assertNull(findResult);
    }

    @Test
    void testDeleteIfNotExist() {
        Product product = productList.get(1);
        Product result = productRepository.save(product);
        Product findResult = productRepository.findById(product.getId());

        assertNotNull(findResult);

        assertThrows(RuntimeException.class, () -> productRepository.delete(UUID.randomUUID()));
    }
}
