package org.ooka.productstore.core;

public class Product {

    private final String name;
    private final String description;
    private final Float price;
    private final Boolean available;

    public Product(String name, String description, Float price, Boolean available) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
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
