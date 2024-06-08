package com.example.demo.service;


import com.example.demo.domain.Marque;
import com.example.demo.repository.MarqueRepository;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MarqueService {

    private final Logger log = LoggerFactory.getLogger(MarqueService.class);

    private final MarqueRepository marqueRepository;

    public MarqueService(MarqueRepository marqueRepository) {
        this.marqueRepository = marqueRepository;
    }

    public Marque save(Marque marque) {
        log.debug("Request to save Marque : {}", marque);
        return marqueRepository.save(marque);
    }

    public Page<Marque> getMarquesPage(Document document, Pageable pageable) {
        document = Optional.ofNullable(document).orElse(new Document());
        //        log.debug("doc: {}", document);
        //        // add removed = false by default. removed links should be returned in
        //        document.putIfAbsent("removed", false);
        //        log.debug("doc: {}", document);
        return marqueRepository.filter(document, pageable);
    }

    public Optional<Marque> findOne(String id) {
        log.debug("Request to get Marque : {}", id);
        return marqueRepository.findById(id);
    }

    public void delete(String id) {
        log.debug("Request to delete Marque : {}", id);
        marqueRepository.deleteById(id);
    }
}
