package io.github.rluu.api;

import com.fasterxml.jackson.annotation.JsonProperty;

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