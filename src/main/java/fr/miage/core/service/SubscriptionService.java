package fr.miage.core.service;

import fr.miage.core.entity.Subscription;

import java.util.List;

public interface SubscriptionService {
    Subscription save(Subscription entity);
    void delete(Long id);
    List<Subscription> findAll();
    Subscription findById(Long id);
    Subscription findByName(String name);
}
