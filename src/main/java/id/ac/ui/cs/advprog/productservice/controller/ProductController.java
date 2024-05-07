package id.ac.ui.cs.advprog.productservice.controller;

import id.ac.ui.cs.advprog.productservice.model.Product;
import id.ac.ui.cs.advprog.productservice.model.Tag;
import id.ac.ui.cs.advprog.productservice.service.ProductService;

import id.ac.ui.cs.advprog.productservice.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private TagService tagService;

    @Autowired
    private ProductService productService;

    String productPage = "redirect:/products";

    @GetMapping("/add")
    public String createProductPage(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("allTags", tagService.findAll());
        return "productCreate";
    }

    @PostMapping("")
    public String createProductPost(@ModelAttribute Product product, @RequestParam(value = "tagNames", required = false) String tagNames) {
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
        return productPage;
    }

    @GetMapping("")
    public String productListPage(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "productList";
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam("productId") String productId) {
        productService.delete(productId);
        return productPage;
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable("id") String id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("allTags", tagService.findAll());
        return "productEdit";
    }

    @PostMapping("/edit")
    public String editProductPost(@ModelAttribute Product product, @RequestParam("tagNames") String tagNames) {
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
        return productPage;
    }
}
