package com.example.demo.service.mapper;

import com.example.demo.domain.Produit;
import com.example.demo.service.dto.ProduitDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProduitMapper {

    public static ProduitDto toDto(Produit produit) {
        if (produit == null) {
            return null;
        }

        ProduitDto dto = new ProduitDto();
        dto.setId(produit.getId());
        dto.setLabel(produit.getLabel());
        dto.setMarqueLabel(produit.getMarqueLabel());
        dto.setModelLabel(produit.getModelLabel());
        dto.setCylindre(produit.getCylindre());

        // Convert list of ImgUrl objects to a list of URLs as Strings
        if (produit.getImgUrlList() != null) {
            dto.setImgUrls(produit.getImgUrlList().stream()
                    .map(imgUrl -> imgUrl.getPath()) // Assuming ImgUrl has a `getUrl()` method
                    .collect(Collectors.toList()));
        } else {
            dto.setImgUrls(List.of());
        }

        return dto;
    }

    public static Produit toEntity(ProduitDto dto) {
        if (dto == null) {
            return null;
        }

        Produit produit = new Produit();
        produit.setId(dto.getId());
        produit.setLabel(dto.getLabel());
        produit.setMarqueLabel(dto.getMarqueLabel());
        produit.setModelLabel(dto.getModelLabel());
        produit.setCylindre(dto.getCylindre());

        // Setting other fields if needed
        produit.setImgUrlList(new ArrayList<>()); // Initialize empty list if you plan to handle URLs

        return produit;
    }
}
