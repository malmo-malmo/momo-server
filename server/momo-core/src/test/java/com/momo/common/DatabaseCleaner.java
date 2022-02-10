package com.momo.common;

import com.google.common.base.CaseFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DatabaseCleaner implements InitializingBean {

    @PersistenceContext
    private EntityManager em;

    private List<String> tables;

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

    @Transactional
    public void cleanUp() {
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        for (String table : tables) {
            em.createNativeQuery("TRUNCATE TABLE " + table).executeUpdate();
        }
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    }
}
