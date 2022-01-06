package com.momo.common;

import com.google.common.base.CaseFormat;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Disabled
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
public class RepositoryTest implements InitializingBean {

    @Autowired
    private TestEntityManager entityManager;

    @PersistenceContext
    private EntityManager em;

    private List<String> tables;

    @BeforeEach
    void cleanUp() {
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        for (String table : tables) {
            em.createNativeQuery("TRUNCATE TABLE " + table).executeUpdate();
        }
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    }

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

    @Override
    public void afterPropertiesSet() {
        tables = em.getMetamodel().getEntities().stream()
            .filter(entityType -> entityType.getJavaType().getAnnotation(Entity.class) != null)
            .map(entityType -> {
                Optional<Table> atTable = Optional.ofNullable(entityType.getJavaType().getAnnotation(Table.class));
                return CaseFormat.UPPER_CAMEL
                    .to(CaseFormat.LOWER_UNDERSCORE, atTable.isPresent() ? atTable.get().name() : entityType.getName());
            })
            .collect(Collectors.toList());
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
