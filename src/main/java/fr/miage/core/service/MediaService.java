package fr.miage.core.service;

import fr.miage.core.entity.Media;

import java.util.List;

public interface MediaService {
    Media save(Media entity);
    void delete(Long id);
    List<Media> findAll();
    Media findById(Long id);
    Media findByMediaTitle(String name);
}
