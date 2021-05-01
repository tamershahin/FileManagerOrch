package io.tenera.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationMessage {
    @JsonProperty("Type")
    private String type;
    @JsonProperty("MessageId")
    private String messageId;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("MessageAttributes")
    private Map<String, MessageAttributes> attributeMap;

    @JsonIgnore
    public FileCreatedEvent getObject(ObjectMapper mapper) {
        try {
            return mapper.readValue(this.message, FileCreatedEvent.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "NotificationMessage{" +
                "type='" + type + '\'' +
                ", messageId='" + messageId + '\'' +
                ", message='" + message + '\'' +
                ", attributeMap=" + attributeMap +
                '}';
    }
}
