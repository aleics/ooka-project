package org.ooka.productstore.core;


import org.ooka.productstore.db.ProductsDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductsRepository {

    private ProductsDAO productsDAO;

    public ProductsRepository(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;

        try {
            this.productsDAO.createProductsTable();
        } catch (Exception e) {
            System.out.println("WARN: `products` table couldn't be created. Possibly, already defined");
        }
    }

    public List<Product> getAllProducts(AllProductsFilter filter) {
        if (filter != null) {
            return this.productsDAO.findProductByNameOrDescription(filter.getQuery());
        }
        return this.productsDAO.findAllProducts();
    }

    public Product getProduct(ProductFilter filter) {
        return this.productsDAO.findProductById(filter.getId());

    }

    public void saveProduct(Product product) {
        this.productsDAO.insert(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAvailable()
        );
    }

}
