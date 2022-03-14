package com.momo;

import com.momo.domain.auth.repository.AccessTokenReissuanceRepository;
import com.momo.domain.group.search.GroupSearchEngine;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(excludeFilters = @ComponentScan.Filter(
    type = FilterType.ASSIGNABLE_TYPE,
    classes = {GroupSearchEngine.class, AccessTokenReissuanceRepository.class})
)
public class JpaRepositoryScan {

}
