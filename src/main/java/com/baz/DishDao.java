package com.baz;

import org.hibernate.Criteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by arahis on 5/9/17.
 */
public class DishDao {

    public void addDish(Dish dish){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction().begin();
        em.persist(dish);
        em.getTransaction().commit();
        em.close();
    }

    public List<Dish> getInPriceRange(int min, int max) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Dish> cq = cb.createQuery(Dish.class);
        Root<Dish> dishRoot = cq.from(Dish.class);
        cq.select(dishRoot)
                .where(cb.between(dishRoot.get("price"),min,max));
        List<Dish> dishes = em.createQuery(cq).getResultList();
        em.close();

        return dishes;
    }

    public List<Dish> getWithDiscount() {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        int noDiscount = 0;
        CriteriaQuery<Dish> cq = cb.createQuery(Dish.class);
        Root<Dish> dishRoot = cq.from(Dish.class);
        cq.select(dishRoot)
                .where(cb.gt(dishRoot.get("discount"), noDiscount));
        List<Dish> dishes = em.createQuery(cq).getResultList();
        em.close();

        return dishes;
    }

    public List<Dish> getAll() {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Dish> cq = cb.createQuery(Dish.class);
        Root<Dish> dishRoot = cq.from(Dish.class);
        cq.select(dishRoot);

        List<Dish> dishes = em.createQuery(cq).getResultList();
        em.close();

        return dishes;
    }
}
