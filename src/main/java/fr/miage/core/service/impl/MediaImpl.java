package fr.miage.core.service.impl;

import fr.miage.core.entity.Media;
import fr.miage.core.repository.MediaRepository;
import fr.miage.core.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MediaImpl implements MediaService {
    @Autowired
    MediaRepository mediaRepository;

    @Override
    public Media save(Media entity) {
        return mediaRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
            mediaRepository.deleteById(id);
    }

    @Override
    public List<Media> findAll() {
        return mediaRepository.findAll();
    }

    @Override
    public Media findById(Long id) {
        final Optional<Media> media= mediaRepository.findById(id);
        if(media.isPresent()){
            return media.get();
        }
        return null;
    }

    @Override
    public Media findByMediaTitle(String name) {
        return mediaRepository.findByMediaTitle(name);
    }
}
