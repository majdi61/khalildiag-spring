package com.example.demo.rest;


import com.example.demo.domain.ImgUrl;
import com.example.demo.domain.Produit;
import com.example.demo.repository.ProduitRepository;
import com.example.demo.service.ProduitService;
import com.example.demo.service.dto.ProduitDto;
import com.turkraft.springfilter.boot.Filter;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing {@link com.khalildiag.service.domain.Produit}.
 */
@RestController
@RequestMapping("/api/produits")
public class ProduitResource {

    private final Logger log = LoggerFactory.getLogger(ProduitResource.class);

    private static final String ENTITY_NAME = "khalildiagProduit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProduitService produitService;
    private final ProduitRepository produitRepository;



    public ProduitResource(ProduitService produitService, ProduitRepository produitRepository) {
        this.produitService = produitService;
        this.produitRepository = produitRepository;
    }

    @CrossOrigin(origins = "https://khalildiag-web-admin.web.app/")
    @GetMapping("")
    public Page<ProduitDto> getProduitsDtoPage(@Filter(entityClass = ProduitDto.class) Document document, Pageable pageable) {
        return produitService.getProduitsPage(document, pageable);
    }


    @GetMapping("/test-produits")
    public ResponseEntity<List<ProduitDto>> testProduits() {
        try {
            // Temporarily return a static response to isolate issues
            return ResponseEntity.ok(Arrays.asList(new ProduitDto("1", "ref1", "label1", "denomination1", "etat1", "marqueLabel1", "modelLabel1", "categoryLabel1", new ArrayList<>())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @CrossOrigin(origins = "https://khalildiag-web-admin.web.app/")
    @PostMapping("")
    public ResponseEntity<Produit> saveProduit(@RequestBody Produit produit) throws URISyntaxException {
        Produit result = produitService.save(produit);
        return ResponseEntity
            .created(new URI("/api/produits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    @CrossOrigin(origins = "https://khalildiag-web-admin.web.app/")
    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable("id") String id) {
        log.debug("REST request to get Produit : {}", id);
        Optional<Produit> produit = produitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(produit);
    }

    @CrossOrigin(origins = "https://khalildiag-web-admin.web.app/")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable("id") String id) {
        log.debug("REST request to delete Produit : {}", id);
        produitService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }

    private ProduitDto convertToDto(Produit produit) {
        List<String> imgUrls = produit.getImgUrlList().stream()
                .map(ImgUrl::getPath) // Assuming ImgUrl has a getUrl method
                .collect(Collectors.toList());
        return new ProduitDto(
                produit.getId(),
                produit.getLabel(),
                produit.getMarqueLabel(),
                produit.getModelLabel(),
                produit.getCylindre(),
                imgUrls
        );
    }
}
