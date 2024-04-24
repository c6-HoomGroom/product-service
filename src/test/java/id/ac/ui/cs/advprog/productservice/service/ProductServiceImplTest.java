package id.ac.ui.cs.advprog.productservice.service;

import id.ac.ui.cs.advprog.productservice.model.Product;
import id.ac.ui.cs.advprog.productservice.model.Tag;
import id.ac.ui.cs.advprog.productservice.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    List<Product> products;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();

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

        products.add(product);
        products.add(product2);
        products.add(product3);

    }

    @AfterEach
    void tearDown() {
        products.clear();
    }

    Product mockUpdate(Product product) {
        int i = 0;
        for (Product savedProduct:products) {
            if (savedProduct.getId().equals(product.getId())) {
                products.remove(i);
                products.add(i, product);
                return product;
            }
            i+=1;
        }
        return null;
    }

    Product mockFindById(UUID id) {
        for (Product savedProduct : products) {
            if (savedProduct.getId().equals(id)) {
                return savedProduct;
            }
        }
        return null;
    }

    @Test
    void testCreate() {
        Product product = products.getFirst();
        when(productRepository.save(product)).thenReturn(product);
        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals(product, createdProduct);

        verify(productRepository, times(1)).save(product);
    }
    @Test
    void testUpdate() {
        Product product1 = products.getFirst();
        Product product2 = products.getLast();
        product2.setId(product1.getId());

        when(productRepository.update(product2)).thenReturn(mockUpdate(product2));
        productService.update(product2);

        product1 = products.getFirst();

        assertEquals(product1.getId(), product2.getId());
        assertEquals(product1.getName(), product2.getName());
        assertEquals(product1.getDescription(), product2.getDescription());
        assertEquals(product1.getPrice(), product2.getPrice());

        verify(productRepository, times(1)).update(product2);
    }
    @Test
    void testUpdateIfNotFound() {
        Product product = new Product();

        when(productRepository.update(product)).thenReturn(mockUpdate(product));
        Product updatedProduct = productService.update(product);

        assertNull(updatedProduct);

        verify(productRepository, times(1)).update(product);
    }
    @Test
    void testDelete() {
        Product product = products.get(1);
        doAnswer(invocation -> {
            products.remove(product);
            return null;
        }).when(productRepository).delete(product.getId());

        when(productRepository.findAll()).thenReturn(products);

        List<Product> current = productService.findAll();
        assertEquals(3, current.size());

        productService.delete(product.getId().toString());

        List<Product> afterDelete = productService.findAll();
        assertEquals(2, afterDelete.size());

        verify(productRepository, times(1)).delete(product.getId());
    }
    @Test
    void testDeleteIfNotFound() {
        UUID productId = UUID.fromString("12345678-1234-1234-1234-123456789abc");
        doAnswer(invocation -> {
            throw new RuntimeException("Product not found for id: " + productId.toString());
        }).when(productRepository).delete(productId);

        assertThrows(RuntimeException.class, () -> productService.delete(productId.toString()));

        verify(productRepository, times(1)).delete(productId);
    }
    @Test
    void testFindById() {
        Product product = products.get(1);
        UUID productId = product.getId();
        when(productRepository.findById(productId)).thenReturn(mockFindById(productId));
        Product foundProduct = productService.findById(productId.toString());

        assertNotNull(foundProduct);
        assertEquals(foundProduct.getId(), product.getId());
        assertEquals(foundProduct.getName(), product.getName());
        assertEquals(foundProduct.getDescription(), product.getDescription());
        assertEquals(foundProduct.getPrice(), product.getPrice());

        verify(productRepository, times(1)).findById(productId);
    }
    @Test
    void testFindAll() {
        when(productRepository.findAll()).thenReturn(products);

        List<Product> allProducts = productService.findAll();

        assertEquals(products.size(), allProducts.size());

        verify(productRepository, times(1)).findAll();
    }
}
