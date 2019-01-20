package fr.miage.core.service.impl;

import fr.miage.core.entity.Subscription;
import fr.miage.core.entity.User;
import fr.miage.core.repository.SubscriptionRepository;
import fr.miage.core.service.SubscriptionService;
import fr.miage.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    UserService userService;

    @Override
    public Subscription save(Subscription entity) {
//        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
//        if (currentUser != "anonymousUser"){
//            Optional<User> user = userService.findByUserName(currentUser);
//            entity.setUser_sub(user.get());
//            return subscriptionRepository.save(entity);
//        }
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
    public Subscription findBySubscriptionId(Long id) {
        return subscriptionRepository.findBySubscriptionId(id);
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
