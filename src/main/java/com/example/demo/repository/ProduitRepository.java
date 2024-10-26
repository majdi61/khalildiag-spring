package com.example.demo.repository;

import com.example.demo.domain.Produit;
import com.example.demo.service.dto.ProduitDto;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends MongoRepository<Produit, String> {
    @Query("?0")
    List<Produit> filter(Document document);

    @Query("?0")
    Page<ProduitDto> filter(Document document, Pageable pageable);





}
