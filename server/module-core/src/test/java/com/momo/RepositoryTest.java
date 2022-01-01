package com.momo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@Disabled
@DataJpaTest
@Transactional
@ExtendWith(SpringExtension.class)
@ActiveProfiles(TestProfile.LOCAL)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
public class RepositoryTest {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    private EntityTransaction transaction;

    @BeforeEach
    public void before() {
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    protected <T> T save(T entity) {
        transaction.begin();
        try {
            entityManager.persist(entity);
            entityManager.flush();
            transaction.commit();
            entityManager.clear();
        } catch (Exception e) {
            transaction.rollback();
        }
        return entity;
    }

    protected <T> Iterable<T> saveAll(Iterable<T> entities) {
        transaction.begin();

        for (T entity : entities) {
            try {
                entityManager.persist(entity);
                entityManager.flush();
                transaction.commit();
                entityManager.clear();
            } catch (Exception e) {
                transaction.rollback();
            }
        }
        return entities;
    }
}

@TestConfiguration
class TestConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}