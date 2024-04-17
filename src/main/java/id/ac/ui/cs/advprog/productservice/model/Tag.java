package id.ac.ui.cs.advprog.productservice.model;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter @Setter
public class Tag {
    private UUID id;
    private String name;

    public Tag() {
        this.id = UUID.randomUUID();
    }

    public Tag(String name) {
        this();
        this.setName(name);
    }
}

