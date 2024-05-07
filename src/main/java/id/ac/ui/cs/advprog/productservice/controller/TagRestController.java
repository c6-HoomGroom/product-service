package id.ac.ui.cs.advprog.productservice.controller;

import id.ac.ui.cs.advprog.productservice.model.Tag;
import id.ac.ui.cs.advprog.productservice.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/tags")
public class TagRestController {
    @Autowired
    private TagService tagService;

    Logger logger = Logger.getLogger(getClass().getName());

    @GetMapping("/api/id/{id}")
    public ResponseEntity getTagById(@PathVariable String id) {
        ResponseEntity responseEntity = null;
        try {
            Tag tag = tagService.findById(id);
            if (tag != null) {
                responseEntity = ResponseEntity.ok(tag);
            } else {
                responseEntity = ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            String errMessage = "Failed to retrieve tag with id: " + id;
            logger.info(errMessage);
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errMessage);
        }
        return responseEntity;
    }

    @GetMapping("/api/name/{name}")
    public ResponseEntity getTagByName(@PathVariable String name) {
        ResponseEntity responseEntity = null;
        try {
            Tag tag = tagService.findByName(name);
            if (tag != null) {
                responseEntity = ResponseEntity.ok(tag);
            } else {
                responseEntity = ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            String errMessage = "Failed to retrieve tag with name: " + name;
            logger.info(errMessage);
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errMessage);
        }
        return responseEntity;
    }

    @GetMapping("/api")
    public ResponseEntity getAllTag() {
        ResponseEntity responseEntity = null;
        try {
            List<Tag> tagList = tagService.findAll();
            responseEntity = ResponseEntity.ok(tagList);
        } catch (Exception e) {
            String errMessage = "Failed to retrieve all tags.";
            logger.info(errMessage);
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errMessage);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/api/delete/{tagId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTag(@PathVariable String tagId) {
        ResponseEntity responseEntity = null;
        try {
            tagService.delete(tagId);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            String errMessage = "Failed to delete tag.";
            logger.info(errMessage);
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errMessage);
        }
        return responseEntity;
    }

    @PostMapping("/api")
    public ResponseEntity createTag(@RequestBody Tag tag) {
        ResponseEntity responseEntity = null;
        try {
            tagService.create(tag);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            String errMessage = "Failed to create tag.";
            logger.info(errMessage);
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errMessage);
        }
        return responseEntity;
    }
}
