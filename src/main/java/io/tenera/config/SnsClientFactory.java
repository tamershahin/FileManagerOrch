package io.tenera.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.env.Environment;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

import javax.inject.Singleton;

@Factory
public class SnsClientFactory {

    @Singleton
    AmazonSNS snsClient(Environment environment, SnsFifoMessageGroupIdHandler snsFifoMessageGroupIdHandler) {
        String endpoint = "http://localhost:4566";
        String region = "eu-central-1";

        return AmazonSNSClient.builder()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("foo", "bar")))
                .withRequestHandlers(snsFifoMessageGroupIdHandler)
                .build();
    }

    @Singleton
    NotificationMessagingTemplate notificationMessagingTemplate(AmazonSNS amazonSNS, MappingJackson2MessageConverter messageConverter) {
        var notificationMessagingTemplate = new NotificationMessagingTemplate(amazonSNS);
        notificationMessagingTemplate.setMessageConverter(messageConverter);
        return notificationMessagingTemplate;
    }

    @Singleton
    MappingJackson2MessageConverter jacksonMessageConverter(ObjectMapper objectMapper) {
        var messageConverter = new MappingJackson2MessageConverter();

        // use our objectMapper, not the one created inside the MappingJackson2MessageConverter, or it will miss
        // some configuration option
        messageConverter.setObjectMapper(objectMapper);

        // otherwise the message will be encoded as byte[] which is then toString()ed (leading to [B@7591083d)
        messageConverter.setSerializedPayloadClass(String.class);
        return messageConverter;
    }

}