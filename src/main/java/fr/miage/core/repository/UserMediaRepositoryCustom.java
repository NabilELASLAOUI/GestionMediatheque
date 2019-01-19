package fr.miage.core.repository;

import fr.miage.core.entity.Media;
import fr.miage.core.entity.UserMedia;
import fr.miage.core.entity.UserMediaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMediaRepositoryCustom{

    UserMedia findByUserMediaId(UserMediaId userMediaId);
}
