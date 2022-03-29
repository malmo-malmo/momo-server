package com.momo.config;

import com.momo.group.search.GroupSearchEngine;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackageClasses = GroupSearchEngine.class)
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    private final String uris;
    private final String username;
    private final String password;

    public ElasticSearchConfig(
        @Value("${spring.elasticsearch.rest.uris}") String uris,
        @Value("${spring.elasticsearch.rest.username}") String username,
        @Value("${spring.elasticsearch.rest.password}") String password
    ) {
        this.uris = uris;
        this.username = username;
        this.password = password;
    }

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
            .connectedTo(uris)
            .usingSsl()
            .withBasicAuth(username, password)
            .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
