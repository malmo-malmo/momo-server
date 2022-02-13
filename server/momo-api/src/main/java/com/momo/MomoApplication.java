package com.momo;

import com.momo.config.JasyptConfig;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.ulisesbocchio.jasyptspringboot.environment.StandardEncryptableEnvironment;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EnableEncryptableProperties
@RequiredArgsConstructor
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
