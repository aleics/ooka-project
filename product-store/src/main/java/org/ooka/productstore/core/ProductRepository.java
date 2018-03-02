package org.ooka.productstore.core;


import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private final List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product("1234", "Product1", "Description1", 12.3f, true));
        products.add(new Product("5678","Product2", "Description2", 15.2f, false));
    }

    public List<Product> getAllProducts(ProductFilter filter) {
        List<Product> allProducts = new ArrayList<>(products);

        if (filter != null) {
            allProducts.removeIf(product ->
                    !product.getId().equals(filter.getId())
            );
        }

        return allProducts;
    }

    public void saveProduct(Product product) {
        products.add(product);
    }

}
