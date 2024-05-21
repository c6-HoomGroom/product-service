package id.ac.ui.cs.advprog.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tag")
@Getter @Setter
public class Tag {
    @Id
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private Set<Product> products;

    public Tag() {
        this.id = UUID.randomUUID();
    }

    public Tag(String name) {
        this();
        this.setName(name);
    }
}

