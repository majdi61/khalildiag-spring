package com.example.demo.repository;

import com.example.demo.domain.Model;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data MongoDB repository for the Model entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModelRepository extends MongoRepository<Model, String> {
    @Query("?0")
    List<Model> filter(Document document);

    @Query("?0")
    Page<Model> filter(Document document, Pageable pageable);
}
