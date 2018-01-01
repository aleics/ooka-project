package org.ooka.productstore.core;


import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private final List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product("Product1", "Description1", 12.3f, true));
        products.add(new Product("Product2", "Description2", 15.2f, false));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public void saveProduct(Product product) {
        products.add(product);
    }
}
