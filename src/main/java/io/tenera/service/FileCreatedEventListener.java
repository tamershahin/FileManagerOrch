package io.tenera.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.jms.annotations.JMSListener;
import io.micronaut.jms.annotations.Queue;
import io.micronaut.messaging.annotation.MessageBody;

import static io.micronaut.jms.sqs.configuration.SqsConfiguration.CONNECTION_FACTORY_BEAN_NAME;

@JMSListener(CONNECTION_FACTORY_BEAN_NAME)
public class FileCreatedEventListener {

    private final ObjectMapper mapper;

    public FileCreatedEventListener(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Queue(value = "file-created.fifo", concurrency = "1-1" )
    public void receive(@MessageBody NotificationMessage body) {
        System.out.println("Received body = " + body.toString());
        System.out.println("Received object = " + body.getObject(mapper));
    }
}