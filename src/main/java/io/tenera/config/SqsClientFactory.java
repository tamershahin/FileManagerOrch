package io.tenera.config;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.env.Environment;

import javax.inject.Singleton;

@Factory
public class SqsClientFactory {

    @Singleton
    AmazonSQS sqsClient(Environment environment) {
        String endpoint = "http://localhost:4566";
        String region = "eu-central-1";
        return AmazonSQSClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("foo", "bar")))
                .build();

    }
}
