package org.ooka.productstore.core;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductRepository {

    private final List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product("1234", "Product1", "Description1", 12.3f, true));
        products.add(new Product("5678","Product2", "Description2", 15.2f, false));
    }

    public List<Product> getAllProducts(AllProductsFilter filter) {
        if (filter != null) {
            Predicate<Product> filterPredicate = element ->
                    element.getName().toLowerCase().contains(filter.getQuery()) ||
                    element.getDescription().toLowerCase().contains(filter.getQuery());

            return products
                    .stream()
                    .filter(filterPredicate)
                    .collect(Collectors.toList());
        }
        return products;
    }

    public Product getProduct(ProductFilter filter) {
        return products
                .stream()
                .filter(element -> element.getId().equals(filter.getId()))
                .findFirst()
                .get();

    }

    public void saveProduct(Product product) {
        products.add(product);
    }

}
