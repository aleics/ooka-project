package org.ooka.productstore.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AllProductsFilter {
    private String query;


    @JsonProperty("q")
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}

