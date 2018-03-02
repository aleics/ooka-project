package org.ooka.productstore.core;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import java.util.List;

public class Query implements GraphQLQueryResolver {

    private final ProductRepository productRepository;

    public Query(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> allProducts(ProductFilter filter) {
        return productRepository.getAllProducts(filter);
    }
}