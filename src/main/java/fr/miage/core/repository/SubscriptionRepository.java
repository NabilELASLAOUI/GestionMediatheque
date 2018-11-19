package fr.miage.core.repository;

import fr.miage.core.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByBeginningDate(Date date);
    List<Subscription> findByEndDate(Date date);
}
