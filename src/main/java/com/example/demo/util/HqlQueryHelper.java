package com.example.demo.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HqlQueryHelper {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Object> findHighValueClaims(double minAmount) {
        String hql = "SELECT c FROM Claim c WHERE c.claimAmount > :amount";
        TypedQuery<Object> query = entityManager.createQuery(hql, Object.class);
        query.setParameter("amount", minAmount);
        return query.getResultList();
    }

    // Example for Criteria API search
    public List<Object> findClaimsByDescriptionKeyword(String keyword) {
        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(Object.class);
        var root = cq.from(Object.class); // Replace Object with actual entity e.g., Claim.class
        cq.select(root)
          .where(cb.like(cb.lower(root.get("description")), "%" + keyword.toLowerCase() + "%"));

        return entityManager.createQuery(cq).getResultList();
    }
}
