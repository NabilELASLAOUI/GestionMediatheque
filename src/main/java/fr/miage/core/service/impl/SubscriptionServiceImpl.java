package fr.miage.core.service.impl;

import fr.miage.core.entity.Subscription;
import fr.miage.core.repository.SubscriptionRepository;
import fr.miage.core.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription save(Subscription entity) {
        return subscriptionRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        subscriptionRepository.deleteById(id);
    }

    @Override
    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription findById(Long id) {
        final Optional<Subscription> media= subscriptionRepository.findById(id);
        if(media.isPresent()){
            return media.get();
        }
        return null;
    }

    @Override
    public List<Subscription> findByBeginningDate(Date date) {
        return subscriptionRepository.findByBeginningDate(date);
    }

    @Override
    public List<Subscription> findByEndDate(Date date) {
        return subscriptionRepository.findByEndDate(date);
    }
}
