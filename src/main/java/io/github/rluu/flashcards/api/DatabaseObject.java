package io.github.rluu.flashcards.api;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatabaseObject {
    @JsonProperty
    private Long id;
    @JsonProperty
    private ZonedDateTime createdDateTimeUtc;
    @JsonProperty
    private Long createdByUserId;
    @JsonProperty
    private ZonedDateTime lastModifiedDateTimeUtc;
    @JsonProperty
    private Long lastModifiedByUserId;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public ZonedDateTime getCreatedDateTimeUtc() {
        return createdDateTimeUtc;
    }
    public void setCreatedDateTimeUtc(ZonedDateTime createdDateTimeUtc) {
        this.createdDateTimeUtc = createdDateTimeUtc;
    }
    public Long getCreatedByUserId() {
        return createdByUserId;
    }
    public void setCreatedByUserId(Long createdByUserId) {
        this.createdByUserId = createdByUserId;
    }
    public ZonedDateTime getLastModifiedDateTimeUtc() {
        return lastModifiedDateTimeUtc;
    }
    public void setLastModifiedDateTimeUtc(ZonedDateTime lastModifiedDateTimeUtc) {
        this.lastModifiedDateTimeUtc = lastModifiedDateTimeUtc;
    }
    public Long getLastModifiedByUserId() {
        return lastModifiedByUserId;
    }
    public void setLastModifiedByUserId(Long lastModifiedByUserId) {
        this.lastModifiedByUserId = lastModifiedByUserId;
    }
}
