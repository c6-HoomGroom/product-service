package id.ac.ui.cs.advprog.productservice.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ProductBuilder {
    private UUID id = UUID.randomUUID();
    private String name;
    private Set<Tag> tags = new HashSet<>();
    private String description;
    private String image;
    private double price;
    private double discountPrice;

    public ProductBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public ProductBuilder name(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty!");
        }
        this.name = name;
        return this;
    }

    public ProductBuilder tag(Tag tag) {
        this.tags.add(tag);
        return this;
    }

    public ProductBuilder tags(Set<Tag> tags) {
        this.tags.addAll(tags);
        return this;
    }

    public ProductBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder image(String image) {
        this.image = image;
        return this;
    }

    public ProductBuilder price(double price) {
        this.price = price;
        return this;
    }

    public ProductBuilder discountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
        return this;
    }

    public Product build() {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setTags(tags);
        product.setDescription(description);
        product.setImage(image);
        product.setPrice(price);
        product.setDiscountPrice(discountPrice);

        return product;
    }
}
