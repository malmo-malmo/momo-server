package com.momo.config;

import com.momo.domain.group.repository.GroupSearchRepository;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackageClasses = GroupSearchRepository.class)
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    @Value("${cloud.aws.elasticsearch.endpoint}")
    private String endpoint;

    @Value("${cloud.aws.elasticsearch.username}")
    private String username;

    @Value("${cloud.aws.elasticsearch.password}")
    private String password;

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
            .connectedTo(endpoint)
            .usingSsl()
            .withBasicAuth(username, password)
            .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
