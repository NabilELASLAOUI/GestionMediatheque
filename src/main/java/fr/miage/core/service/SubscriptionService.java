package fr.miage.core.service;

import fr.miage.core.entity.Subscription;

import java.util.Date;
import java.util.List;

public interface SubscriptionService {
    Subscription save(Subscription entity);
    void delete(Long id);
    List<Subscription> findAll();
    Subscription findBySubscriptionId(Long id);
    List<Subscription> findByBeginningDate(Date date);
    List<Subscription> findByEndDate(Date date);
}
