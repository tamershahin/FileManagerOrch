package io.tenera.service;

//import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Singleton
public class SendMessage implements ApplicationEventListener<StartupEvent> {

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    public SendMessage(NotificationMessagingTemplate notificationMessagingTemplate) {
        this.notificationMessagingTemplate = notificationMessagingTemplate;
    }
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(StartupEvent event) {
        UUID id = UUID.randomUUID();
        logger.info("Sending with id = " + id);
        notificationMessagingTemplate.convertAndSend("file-events.fifo",
                new FileCreatedEvent(id, Instant.now(), "s3://some/url"),
                Map.of("event", "FILE_CREATED")
        );
    }

    @Override
    public boolean supports(StartupEvent event) {
        return ApplicationEventListener.super.supports(event);
    }
}
