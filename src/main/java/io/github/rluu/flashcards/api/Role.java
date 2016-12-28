package io.github.rluu.flashcards.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.rluu.flashcards.api.DatabaseObject;

public class Role extends DatabaseObject {
    private String name;
    private String description;

    @JsonProperty
    public String getName() {
        return name;
    }
    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty
    public String getDescription() {
        return description;
    }
    @JsonProperty
    public void setDescription(String description) {
        this.description = description;
    }
}