package com.example.demo.service;

import com.example.demo.domain.Model;
import com.example.demo.repository.ModelRepository;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModelService {

    private final Logger log = LoggerFactory.getLogger(ModelService.class);

    private final ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public Model save(Model model) {
        log.debug("Request to save Model : {}", model);
        model.setMarqueId(model.getMarque().getId());
        return modelRepository.save(model);
    }

    public Page<Model> getModelsPage(Document document, Pageable pageable) {
        document = Optional.ofNullable(document).orElse(new Document());
        //        log.debug("doc: {}", document);
        //        // add removed = false by default. removed links should be returned in
        //        document.putIfAbsent("removed", false);
        //        log.debug("doc: {}", document);
        return modelRepository.filter(document, pageable);
    }

    public Optional<Model> findOne(String id) {
        log.debug("Request to get Model : {}", id);
        return modelRepository.findById(id);
    }

    public void delete(String id) {
        log.debug("Request to delete Model : {}", id);
        modelRepository.deleteById(id);
    }
}
