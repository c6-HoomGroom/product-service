package id.ac.ui.cs.advprog.productservice.model;

import lombok.Setter;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter @Setter
public class Product {
    private UUID id;
    private String name;
    private Set<Tag> tags;
    private String description;
    private String image;
    private double price;
    private double discountPrice;

    public Product() {}

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }
}
