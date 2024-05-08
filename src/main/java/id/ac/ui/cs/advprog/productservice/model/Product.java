package id.ac.ui.cs.advprog.productservice.model;

import lombok.Setter;
import lombok.Getter;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "product")
@Getter @Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    private String name;

    @ManyToMany
    @JoinTable(
            name = "product_tags",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;
    private String description;
    private String image;
    private double price;
    private double discountPrice;

    public Product() { this.id = UUID.randomUUID(); }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }
}
