package io.tenera.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.jms.annotations.JMSListener;
import io.micronaut.jms.annotations.Queue;
import io.micronaut.messaging.annotation.MessageBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static io.micronaut.jms.sqs.configuration.SqsConfiguration.CONNECTION_FACTORY_BEAN_NAME;

@JMSListener(CONNECTION_FACTORY_BEAN_NAME)
public class FileCreatedEventListener {

    private final ObjectMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public FileCreatedEventListener(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Queue(value = "file-created.fifo", concurrency = "1-1")
    public void receive(@MessageBody NotificationMessage body) throws IOException {
        logger.info("Received body = " + body.toString());
        logger.info("Received object = " + body.getObject(mapper));
        Files.writeString(Path.of("/Users/tamer/test"), body.toString(), StandardOpenOption.APPEND);
    }
}