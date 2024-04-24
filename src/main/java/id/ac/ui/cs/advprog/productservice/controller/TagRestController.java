package id.ac.ui.cs.advprog.productservice.controller;

import id.ac.ui.cs.advprog.productservice.model.Tag;
import id.ac.ui.cs.advprog.productservice.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagRestController {
    @Autowired
    private TagService tagService;

    @GetMapping("/api/id/{id}")
    public Tag getTagById(@PathVariable String id) {
        Tag tag = tagService.findById(id);
        return tag;
    }

    @GetMapping("/api/name/{name}")
    public Tag getTagByName(@PathVariable String name) {
        Tag tag = tagService.findByName(name);
        return tag;
    }

    @GetMapping("/api")
    public List<Tag> getAllTag() {
        return tagService.findAll();
    }
}
