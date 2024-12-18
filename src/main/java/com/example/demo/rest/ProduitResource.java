package com.example.demo.rest;


import com.example.demo.domain.ImgUrl;
import com.example.demo.domain.Produit;
import com.example.demo.repository.ProduitRepository;
import com.example.demo.service.ProduitService;
import com.example.demo.service.dto.ProduitDto;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/produits")
public class ProduitResource {

    private final Logger log = LoggerFactory.getLogger(ProduitResource.class);

    private static final String ENTITY_NAME = "khalildiagProduit";



    private final ProduitService produitService;
    private final ProduitRepository produitRepository;



    public ProduitResource(ProduitService produitService, ProduitRepository produitRepository) {
        this.produitService = produitService;
        this.produitRepository = produitRepository;
    }



    @GetMapping("")
    public Page<Produit> getProduitsPage(@Filter(entityClass = Produit.class) Document document, Pageable pageable) {
        return produitService.getProduitsPage(document, pageable);
    }


    @PostMapping("")
    public ResponseEntity<Produit> saveProduit(@RequestBody Produit produit) throws URISyntaxException {
        Produit result = produitService.save(produit);
        return ResponseEntity
            .created(new URI("/api/produits/" + result.getId()))
            .body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable("id") String id) {
        log.debug("REST request to get Produit : {}", id);
        Optional<Produit> produit = produitService.findOne(id);
        return ResponseEntity.accepted().body(produit.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable("id") String id) {
        log.debug("REST request to delete Produit : {}", id);
        produitService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
