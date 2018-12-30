package fr.miage.core.service;

import fr.miage.core.entity.MediaType;

import java.util.List;

public interface MediaTypeService {
    MediaType save(MediaType entity);
    void delete(Long id);
    List<MediaType> findAll();
    MediaType findByTypeId(Long id);
    MediaType findByTypeName(String name);
}
