package id.ac.ui.cs.advprog.productservice.controller;

import id.ac.ui.cs.advprog.productservice.model.Product;
import id.ac.ui.cs.advprog.productservice.model.Tag;
import id.ac.ui.cs.advprog.productservice.service.ProductService;
import id.ac.ui.cs.advprog.productservice.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@RestController
@RequestMapping("/products")
public class ProductRestController
{
    @Autowired
    private ProductService productService;

    @Autowired
    private TagService tagService;

    Logger logger = Logger.getLogger(getClass().getName());

    @GetMapping("/api/id/{id}")
    public ResponseEntity getProductById(@PathVariable String id) {
        ResponseEntity responseEntity = null;
        try {
            Product product = productService.findById(id);
            if (product != null) {
                responseEntity = ResponseEntity.ok(product);
            } else {
                responseEntity = ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            String errMessage = "Failed to retrieve product with id: " + id;
            logger.info(errMessage);
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errMessage);
        }
        return responseEntity;
    }

    @GetMapping("/api")
    public ResponseEntity getAllProduct() {
        ResponseEntity responseEntity = null;
        try {
            List<Product> productList = productService.findAll();
            responseEntity = ResponseEntity.ok(productList);
        } catch (Exception e) {
            String errMessage = "Failed to retrieve all products.";
            logger.info(errMessage);
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errMessage);
        }
        return responseEntity;
    }

    @PostMapping("/api")
    public ResponseEntity createProductPost(@RequestBody Product product, @RequestParam(value = "tagNames", required = false) String tagNames) {
        ResponseEntity responseEntity = null;

        try {
            Set<Tag> selectedTags = new HashSet<>();
            if (tagNames != null && !tagNames.isEmpty()) {
                String[] tags = tagNames.split(",");
                List<String> tagList = Arrays.asList(tags);

                for (String tagName : tagList) {
                    Tag tag = tagService.findByName(tagName);
                    if (tag != null) {
                        selectedTags.add(tag);
                    }
                }
            }
            product.setTags(selectedTags);
            productService.create(product);

            responseEntity = ResponseEntity.ok().build();

        } catch (Exception e) {
            String errMessage = "Failed to create product.";
            logger.info(errMessage);
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errMessage);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/api/delete/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@PathVariable String productId) {
        ResponseEntity responseEntity = null;
        try {
            productService.delete(productId);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            String errMessage = "Failed to delete product.";
            logger.info(errMessage);
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errMessage);
        }
        return responseEntity;
    }

    @PostMapping("/api/edit")
    public ResponseEntity editProductPost(@RequestBody Product product, @RequestParam(value = "tagNames", required = false) String tagNames) {
        ResponseEntity responseEntity = null;

        try {
            Set<Tag> selectedTags = new HashSet<>();
            String[] tags = tagNames.split(",");
            List<String> tagList = Arrays.asList(tags);

            for (String tagName : tagList) {
                Tag tag = tagService.findByName(tagName);
                if (tag != null) {
                    selectedTags.add(tag);
                }
            }
            product.setTags(selectedTags);
            productService.update(product);
            responseEntity = ResponseEntity.ok().build();

        } catch (Exception e) {
            String errMessage = "Failed to edit product.";
            logger.info(errMessage);
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errMessage);
        }

        return responseEntity;
    }
}
