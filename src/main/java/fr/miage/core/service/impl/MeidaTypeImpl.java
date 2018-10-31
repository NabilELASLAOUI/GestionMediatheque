package fr.miage.core.service.impl;

import fr.miage.core.entity.MediaType;
import fr.miage.core.repository.MediaTypeRepository;
import fr.miage.core.service.MediaTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MeidaTypeImpl implements MediaTypeService {

    @Autowired
    MediaTypeRepository mediaTypeRepository;
    @Override
    public MediaType save(MediaType entity) {
        return mediaTypeRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        mediaTypeRepository.deleteById(id);
    }

    @Override
    public List<MediaType> findAll() {
        return mediaTypeRepository.findAll();
    }

    @Override
    public MediaType findById(Long id) {
        final Optional<MediaType> mediaType = mediaTypeRepository.findById(id);
        if (mediaType.isPresent()) {
            return mediaType.get();
        }
        return null;
    }

    @Override
    public MediaType findByName(String name) {
        return mediaTypeRepository.findByName(name);
    }
}
