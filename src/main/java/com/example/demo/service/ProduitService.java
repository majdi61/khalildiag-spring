package com.example.demo.service;


import com.example.demo.domain.ImgUrl;
import com.example.demo.domain.Produit;
import com.example.demo.repository.ProduitRepository;
import com.example.demo.service.dto.ProduitDto;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProduitService {

    private final Logger log = LoggerFactory.getLogger(ProduitService.class);

    private final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public Produit save(Produit produit) {
        log.debug("Request to save Produit : {}", produit);
        produit.setCategoryId(produit.getCategory().getId());
        produit.setMarqueId(produit.getMarque().getId());
        produit.setModelId(produit.getModel().getId());
        produit.setCategoryLabel(produit.getCategory().getLabel());
        produit.setMarqueLabel(produit.getMarque().getLabel());
        produit.setModelLabel(produit.getModel().getLabel());
        return produitRepository.save(produit);
    }

    public Page<ProduitDto> getProduitsPage(Document document, Pageable pageable) {
        return  produitRepository.filter(document, pageable);
    }

    public Optional<Produit> findOne(String id) {
        log.debug("Request to get Produit : {}", id);
        return produitRepository.findById(id);
    }

    public void delete(String id) {
        log.debug("Request to delete Produit : {}", id);
        produitRepository.deleteById(id);
    }

}
