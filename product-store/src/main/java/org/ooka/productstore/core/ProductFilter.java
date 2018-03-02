package org.ooka.productstore.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductFilter {
    private String id;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
