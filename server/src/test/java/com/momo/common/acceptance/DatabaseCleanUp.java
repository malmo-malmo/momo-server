package com.momo.common.acceptance;

import com.google.common.base.CaseFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DatabaseCleanUp implements InitializingBean {

  @PersistenceContext
  private EntityManager em;

  private List<String> tables;

  @Override
  public void afterPropertiesSet() {
    tables = em.getMetamodel().getEntities().stream()
        .filter(e -> e.getJavaType().getAnnotation(Entity.class) != null)
        .map(e -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName()))
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
