package id.ac.ui.cs.advprog.productservice.controller;

import id.ac.ui.cs.advprog.productservice.model.Tag;
import id.ac.ui.cs.advprog.productservice.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/add")
    public String createTagPage(Model model) {
        Tag tag = new Tag();
        model.addAttribute("tag", tag);
        return "tagCreate";
    }

    @PostMapping("")
    public String createTagPost(@ModelAttribute Tag tag, Model model) {
        tagService.create(tag);
        return "redirect:/tags";
    }

    @GetMapping("")
    public String tagListPage(Model model) {
        List<Tag> tags = tagService.findAll();
        model.addAttribute("tags", tags);
        return "tagList";
    }

    @PostMapping("/delete")
    public String deleteTag(@RequestParam("tagId") String tagId) {
        tagService.delete(tagId);
        return "redirect:/tags";
    }
}