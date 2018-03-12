package org.ooka.productstore.core;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import java.util.List;

public class Query implements GraphQLQueryResolver {

    private final ProductsRepository productsRepository;

    public Query(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> allProducts(AllProductsFilter filter) {
        return productsRepository.getAllProducts(filter);
    }

    public Product product(ProductFilter filter) {
        return productsRepository.getProduct(filter);
    }
}