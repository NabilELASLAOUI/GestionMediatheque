package fr.miage.core.repository;

import fr.miage.core.entity.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaTypeRepository extends JpaRepository<MediaType, Long> {

    MediaType findByTypeName( String name);

}
