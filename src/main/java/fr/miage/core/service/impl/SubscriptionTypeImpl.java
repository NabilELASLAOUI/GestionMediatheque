package fr.miage.core.service.impl;

import fr.miage.core.entity.MediaType;
import fr.miage.core.entity.SubscriptionType;
import fr.miage.core.repository.SubscriptionTypeRepository;
import fr.miage.core.service.SubscriptionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SubscriptionTypeImpl implements SubscriptionTypeService {

    @Autowired
    SubscriptionTypeRepository subscriptionTypeRepository;
    @Override
    public SubscriptionType save(SubscriptionType entity) {
        entity.setTypeName(entity.getTypeName().toUpperCase());
        return subscriptionTypeRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        subscriptionTypeRepository.deleteById(id);
    }

    @Override
    public List<SubscriptionType> findAll() {
        return subscriptionTypeRepository.findAll();
    }

    @Override
    public SubscriptionType findByTypeId(Long id) {
        final Optional<SubscriptionType> subscriptionType = subscriptionTypeRepository.findById(id);
        return subscriptionType.get();
    }

    @Override
    public SubscriptionType findByTypeName(String name) {
        return subscriptionTypeRepository.findByTypeName(name);
    }
}
