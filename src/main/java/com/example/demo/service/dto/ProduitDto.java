package com.example.demo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the Produit entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitDto {
    private String id;             // Assuming you want to expose the ID
    private String label;          // Label
    private String marqueLabel;     // Marque label
    private String modelLabel;      // Model label
    private String cylindre;   // Category label
    private List<String> imgUrls;   // List of image URLs

    public <E> ProduitDto(String s, String ref1, String label1, String denomination1, String etat1, String marqueLabel1, String modelLabel1, String categoryLabel1, ArrayList<E> es) {
    }
}