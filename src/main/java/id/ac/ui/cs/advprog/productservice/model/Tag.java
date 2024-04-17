package id.ac.ui.cs.advprog.productservice.model;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
public class Tag {
    private final UUID id;

    @Setter
    private String name;

    public Tag() {
        this.id = UUID.randomUUID();
    }
}

