package io.tenera.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageAttributes {
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Value")
    private String Value;

    @Override
    public String toString() {
        return "MessageAttributes{" +
                "type='" + type + '\'' +
                ", Value='" + Value + '\'' +
                '}';
    }
}
