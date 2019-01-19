package fr.miage.core.repository.impl;
import fr.miage.core.entity.UserMedia;
import fr.miage.core.entity.UserMediaId;
import fr.miage.core.repository.UserMediaRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class UserMediaRepositoryImpl implements UserMediaRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserMedia findByUserMediaId( UserMediaId userMediaId) {
        final String sql= "select emp from UserMedia emp where emp.pk=:userMediaId";
        final TypedQuery<UserMedia> query= entityManager.createQuery(sql,UserMedia.class);
        query.setParameter("userMediaId",userMediaId);

        return  query.getSingleResult();
    }
}
