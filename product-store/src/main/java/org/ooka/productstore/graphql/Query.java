package org.ooka.productstore.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.ooka.productstore.core.AllProductsFilter;
import org.ooka.productstore.core.Product;
import org.ooka.productstore.core.ProductFilter;
import org.ooka.productstore.core.ProductsRepository;

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