package io.tenera.service;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public class FileCreatedEvent implements Serializable {
    private UUID fileId;
    private Instant timestamp;
    private String url;

    public FileCreatedEvent(UUID fileId, Instant timestamp, String url) {
        this.fileId = fileId;
        this.timestamp = timestamp;
        this.url = url;
    }

    public FileCreatedEvent() {
    }

    public UUID getFileId() {
        return fileId;
    }

    public void setFileId(UUID fileId) {
        this.fileId = fileId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "FileCreatedEvent{" +
                "fileId=" + fileId +
                ", timestamp=" + timestamp +
                ", url='" + url + '\'' +
                '}';
    }
}
