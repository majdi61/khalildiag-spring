package com.example.demo.rest;


import com.example.demo.domain.Category;
import com.example.demo.service.CategoryService;
import com.turkraft.springfilter.boot.Filter;
import org.apache.tomcat.util.http.HeaderUtil;
import org.apache.tomcat.util.http.ResponseUtil;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;


@RestController
@RequestMapping("/api/category")
public class CategoryResource {

    private final Logger log = LoggerFactory.getLogger(CategoryResource.class);

    private static final String ENTITY_NAME = "khalildiagCategory";



    private final CategoryService categoryService;

    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @CrossOrigin(origins = "https://khalildiag-web-admin.web.app/")
    @GetMapping("")
    public Page<Category> getLinksPage(@Filter(entityClass = Category.class) Document document, Pageable pageable) {
        return categoryService.getCategorysPage(document, pageable);
    }



    @CrossOrigin(origins = "https://khalildiag-web-admin.web.app/")
    @PostMapping("")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) throws URISyntaxException {
        log.debug("REST request to save Category : {}", category);

        Category result = categoryService.save(category);
        return ResponseEntity
            .created(new URI("/api/categorys/" + result.getId()))
            .body(result);
    }

    @CrossOrigin(origins = "https://khalildiag-web-admin.web.app/")
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") String id) {
        log.debug("REST request to get Category : {}", id);
        Optional<Category> category = categoryService.findOne(id);
        return ResponseEntity.accepted().body(category.get());
    }

    @CrossOrigin(origins = "https://khalildiag-web-admin.web.app/")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") String id) {
        log.debug("REST request to delete Category : {}", id);
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
