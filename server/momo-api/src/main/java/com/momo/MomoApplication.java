package com.momo;

import com.momo.config.JasyptConfig;
import com.momo.domain.group.repository.GroupSearchRepository;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.ulisesbocchio.jasyptspringboot.environment.StandardEncryptableEnvironment;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEncryptableProperties
@RequiredArgsConstructor
@EnableJpaRepositories(excludeFilters = @ComponentScan.Filter(
    type = FilterType.ASSIGNABLE_TYPE,
    classes = GroupSearchRepository.class)
)
public class MomoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
            .sources(MomoApplication.class)
            .environment(getEnvironment())
            .run(args);
    }

    private static StandardEncryptableEnvironment getEnvironment() {
        String password = Optional.ofNullable(System.getenv("JASYPT_PASSWORD"))
            .orElseThrow(() -> new RuntimeException("프로퍼티 복호화 비밀번호를 찾을 수 없습니다."));

        return new StandardEncryptableEnvironment(
            null,
            null,
            null,
            null,
            JasyptConfig.createStringEncryptor(password),
            null);
    }
}
