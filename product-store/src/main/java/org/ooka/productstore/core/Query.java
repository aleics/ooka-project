package org.ooka.productstore.core;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import java.util.List;

public class Query implements GraphQLQueryResolver {

    private final ProductRepository productRepository;

    public Query(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> allProducts(AllProductsFilter filter) {
        return productRepository.getAllProducts(filter);
    }

    public Product product(ProductFilter filter) {
        return productRepository.getProduct(filter);
    }
}