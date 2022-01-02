package com.momo.common;

import com.momo.TestProfile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Disabled
@DataJpaTest
@ActiveProfiles(TestProfile.LOCAL)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
public class RepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    protected <T> T save(T entity) {
        entityManager.persist(entity);

        return entity;
    }

    protected <T> Iterable<T> saveAll(Iterable<T> entities) {
        for (T entity : entities) {
            entityManager.persist(entity);
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