package com.example.demo.rest;


import com.example.demo.domain.Model;
import com.example.demo.service.ModelService;
import com.turkraft.springfilter.boot.Filter;
import org.apache.tomcat.util.http.HeaderUtil;
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
@RequestMapping("/api/models")
public class ModelResource {

    private final Logger log = LoggerFactory.getLogger(ModelResource.class);

    private static final String ENTITY_NAME = "khalildiagModel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ModelService modelService;

    public ModelResource(ModelService modelService) {
        this.modelService = modelService;
    }

    @CrossOrigin(origins = "https://khalildiag-web-admin.web.app/")
    @GetMapping("")
    public Page<Model> getLinksPage(@Filter(entityClass = Model.class) Document document, Pageable pageable) {
        return modelService.getModelsPage(document, pageable);
    }

    @CrossOrigin(origins = "https://khalildiag-web-admin.web.app/")
    @PostMapping("")
    public ResponseEntity<Model> saveModel(@RequestBody Model model) throws URISyntaxException {
        log.debug("REST request to save Model : {}", model);
        Model result = modelService.save(model);
        return ResponseEntity
            .created(new URI("/api/models/" + result.getId()))
            .body(result);
    }

    @CrossOrigin(origins = "https://khalildiag-web-admin.web.app/")
    @GetMapping("/{id}")
    public ResponseEntity<Model> getModelById(@PathVariable("id") String id) {
        log.debug("REST request to get Model : {}", id);
        Optional<Model> model = modelService.findOne(id);
        return ResponseEntity.accepted().body(model.get());
    }

    @CrossOrigin(origins = "https://khalildiag-web-admin.web.app/")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable("id") String id) {
        log.debug("REST request to delete Model : {}", id);
        modelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
