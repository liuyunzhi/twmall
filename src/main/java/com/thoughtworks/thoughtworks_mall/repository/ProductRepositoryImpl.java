package com.thoughtworks.thoughtworks_mall.repository;

import com.thoughtworks.thoughtworks_mall.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

public class ProductRepositoryImpl implements ProductRespositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> filter(Double minPrice, Double maxPrice, String brand, String category, Integer page, Integer pageSize, String order) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Product> query = builder.createQuery(Product.class);
        final Root<Product> from = query.from(Product.class);

        Predicate predicate = builder.conjunction();
        if (minPrice != null) {
            predicate = builder.and(predicate, builder.greaterThan(from.get("price"),minPrice));
        }
        if (maxPrice != null) {
            predicate = builder.and(predicate, builder.lessThan(from.get("price"),maxPrice));
        }
        if (brand != null && !brand.isEmpty()) {
            predicate = builder.and(predicate, builder.like(from.get("brand"),brand));
        }
        if (category != null && !category.isEmpty()) {
            predicate = builder.and(predicate, builder.like(from.get("category"),category));
        }

        Order priceOrder = order.equals("DESC") ? builder.desc(from.get("price")) : builder.asc(from.get("price"));

        final CriteriaQuery<Product> criteriaQuery = query.where(predicate).orderBy(priceOrder);

        return entityManager.createQuery(criteriaQuery).setFirstResult(page * pageSize).setMaxResults(pageSize).getResultList();
    }
}
