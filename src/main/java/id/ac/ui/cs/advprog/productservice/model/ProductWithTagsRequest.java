package id.ac.ui.cs.advprog.productservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductWithTagsRequest {
    private Product product;
    private String tagNames;
}

