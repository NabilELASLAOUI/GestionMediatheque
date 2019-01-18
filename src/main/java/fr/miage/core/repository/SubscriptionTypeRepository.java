package fr.miage.core.repository;

import fr.miage.core.entity.MediaType;
import fr.miage.core.entity.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionType , Long> {

    SubscriptionType findByTypeName(String name);

}
