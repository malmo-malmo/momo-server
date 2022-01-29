package com.momo.common;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringContainerTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private EntityManager em;

    @AfterEach
    void tearDown() {
        databaseCleaner.cleanUp();
    }

    protected <T> T save(T entity) {
        em.persist(entity);
        return entity;
    }
}
