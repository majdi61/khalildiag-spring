package com.example.demo.rest;


import com.example.demo.domain.Marque;
import com.example.demo.service.MarqueService;
import com.turkraft.springfilter.boot.Filter;
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

/**
 * REST controller for managing {@link com.khalildiag.service.domain.Marque}.
 */
@RestController
@RequestMapping("/api/marques")
public class MarqueResource {

    private final Logger log = LoggerFactory.getLogger(MarqueResource.class);

    private static final String ENTITY_NAME = "khalildiagMarque";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MarqueService marqueService;

    public MarqueResource(MarqueService marqueService) {
        this.marqueService = marqueService;
    }

    @CrossOrigin(origins = "https://khalildiag-web-admin.web.app/")
    @GetMapping("")
    public Page<Marque> getLinksPage(@Filter(entityClass = Marque.class) Document document, Pageable pageable) {
        return marqueService.getMarquesPage(document, pageable);
    }

    @CrossOrigin(origins = "origins = \"https://khalildiag-web-admin.web.app/")
    @PostMapping("")
    public ResponseEntity<Marque> saveMarque(@RequestBody Marque marque) throws URISyntaxException {
        log.debug("REST request to save Marque : {}", marque);

        Marque result = marqueService.save(marque);
        return ResponseEntity
            .created(new URI("/api/marques/" + result.getId()))
            .body(result);
    }

    @CrossOrigin(origins = "https://khalildiag-web-admin.web.app/")
    @GetMapping("/{id}")
    public ResponseEntity<Marque> getMarqueById(@PathVariable("id") String id) {
        log.debug("REST request to get Marque : {}", id);
        Optional<Marque> marque = marqueService.findOne(id);
        return ResponseEntity.accepted().body(marque.get());
    }

    @CrossOrigin(origins = "https://khalildiag-web-admin.web.app/")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarque(@PathVariable("id") String id) {
        log.debug("REST request to delete Marque : {}", id);
        marqueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
