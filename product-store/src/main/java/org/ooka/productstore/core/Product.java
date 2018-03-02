package org.ooka.productstore.core;

public class Product {

    private final String id;
    private final String name;
    private final String description;
    private final Float price;
    private final Boolean available;

    public Product(String id, String name, String description, Float price, Boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Float getPrice() {
        return price;
    }

    public Boolean getAvailable() {
        return available;
    }
}
