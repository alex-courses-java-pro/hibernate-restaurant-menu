package com.baz;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by arahis on 5/9/17.
 */
public enum PersistenceManager {
    INSTANCE;

    private EntityManagerFactory emFactory;

    PersistenceManager() {
        emFactory = Persistence.createEntityManagerFactory("h1");
    }

    public EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    public void close() {
        emFactory.close();
    }
}
