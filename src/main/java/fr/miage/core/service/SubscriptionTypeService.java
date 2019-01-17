package fr.miage.core.service;

import fr.miage.core.entity.MediaType;
import fr.miage.core.entity.SubscriptionType;

import java.util.List;

public interface SubscriptionTypeService {
    SubscriptionType save(SubscriptionType  entity);
    void delete(Long id);
    List<SubscriptionType> findAll();
    SubscriptionType findByTypeId(Long id);
    SubscriptionType findByTypeName(String name);
}
