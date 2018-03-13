package org.ooka.productstore.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.ooka.productstore.core.Product;
import org.ooka.productstore.core.ProductsRepository;

import java.util.UUID;

public class Mutation implements GraphQLMutationResolver {
    private final ProductsRepository productsRepository;

    public Mutation(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    //  createProduct(name: String!, description: String, price: Float!, available: Boolean!)
    public Product createProduct(String name, String description, Float price, Boolean available) {
        try {
            String productId = UUID.randomUUID().toString();
            Product product = new Product(
                    productId,
                    name,
                    description,
                    price,
                    available
            );

            this.productsRepository.saveProduct(product);
            return product;
        } catch (Exception e) {
            return null;
        }
    }

}
