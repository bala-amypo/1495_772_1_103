package com.example.demo.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class HqlQueryHelper {
    
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Object> findHighValueClaims(double minAmount) {
        return entityManager.createQuery("SELECT c FROM Claim c WHERE c.claimAmount > :amount")
                .setParameter("amount", minAmount)
                .getResultList();
    }

    public List<Object> findClaimsByDescriptionKeyword(String keyword) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> query = cb.createQuery(Object.class);
        Root<Object> root = query.from(Object.class);
        
        query.select(root)
             .where(cb.like(cb.lower(root.get("description")), "%" + keyword.toLowerCase() + "%"));
        
        return entityManager.createQuery(query).getResultList();
    }
}