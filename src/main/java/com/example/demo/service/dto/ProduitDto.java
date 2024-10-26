package com.example.demo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * A DTO for the Produit entity.
 */
@Data
@NoArgsConstructor
public class ProduitDto {
    private String id;             // Assuming you want to expose the ID
    private String label;          // Label
    private String marqueLabel;     // Marque label
    private String modelLabel;      // Model label
    private String cylindre;   // Category label
    private List<String> imgUrls;   // List of image URLs

    public ProduitDto(String id, String ref, String label, String denomination, String etat, String marqueLabel, String modelLabel, String categoryLabel, List<String> imgUrls) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMarqueLabel() {
        return marqueLabel;
    }

    public void setMarqueLabel(String marqueLabel) {
        this.marqueLabel = marqueLabel;
    }

    public String getModelLabel() {
        return modelLabel;
    }

    public void setModelLabel(String modelLabel) {
        this.modelLabel = modelLabel;
    }

    public String getCylindre() {
        return cylindre;
    }

    public void setCylindre(String cylindre) {
        this.cylindre = cylindre;
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public ProduitDto(String id, String label, String marqueLabel, String modelLabel, String cylindre, List<String> imgUrls) {
        this.id = id;
        this.label = label;
        this.marqueLabel = marqueLabel;
        this.modelLabel = modelLabel;
        this.cylindre = cylindre;
        this.imgUrls = imgUrls;
    }
}