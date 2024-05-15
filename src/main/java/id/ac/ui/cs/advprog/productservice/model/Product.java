package id.ac.ui.cs.advprog.productservice.model;

import lombok.Setter;
import lombok.Getter;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "product")
@Getter @Setter
public class Product {
    @Id
    private UUID id;

    @ManyToMany
    @JoinTable(
            name="product_tags",
            joinColumns= @JoinColumn(name="product_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="tag_id", referencedColumnName="id")
    )
    private Set<Tag> tags;

    private String name;
    private String description;
    private String image;
    private double price;
    private double discountPrice;

    public Product() { this.id = UUID.randomUUID(); }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }
}
