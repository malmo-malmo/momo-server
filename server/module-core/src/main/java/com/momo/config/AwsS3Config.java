package com.momo.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsS3Config {

    private final String accessKey;

    private final String secretKey;

    private final String region;

    public AwsS3Config(
        @Value("${cloud.aws.credentials.access-key}") String accessKey,
        @Value("${cloud.aws.credentials.secret-key}") String secretKey,
        @Value("${cloud.aws.region.static}") String region) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.region = region;
    }

    @Bean
    public AmazonS3Client amazonS3Client() {
        String awsEndpoint = "s3.%s.amazonaws.com";
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration(String.format(awsEndpoint, region), region))
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .build();
    }
}
