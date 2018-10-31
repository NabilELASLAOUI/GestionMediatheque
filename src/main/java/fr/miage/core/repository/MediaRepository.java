package fr.miage.core.repository;

import fr.miage.core.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MediaRepository extends JpaRepository<Media, Long> {
    Media findByName(String name);
}
