package id.ac.ui.cs.advprog.productservice.model;

import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import lombok.Singular;

import java.util.Set;
import java.util.UUID;

@Builder
@Getter @Setter
public class Product {
    @Builder.Default private UUID id = UUID.randomUUID();
    private String name;
    @Singular("tag") private Set<Tag> tag;
    private String description;
    private String image;
    private double price;
    private double discountPrice;
}
