package org.ooka.productstore.core;


import org.ooka.productstore.db.ProductsDAO;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.util.List;

public class ProductsRepository {

    private DBI jdbi;

    private ProductsDAO getProductsDAO() {
        try {
            Handle handle = jdbi.open();
            return handle.attach(ProductsDAO.class);
        } catch (Exception e) {
            System.out.println("ERROR: Couldn't attach ProductsDAO to JDBI instance. Exception: " + e);
            return null;
        }
    }

    public ProductsRepository(DBI jdbi) {
        this.jdbi = jdbi;

        try {
            this.getProductsDAO().createProductsTable();
        } catch (Exception e) {
            System.out.println("WARN: `products` table couldn't be created. Possibly, already defined. Exception: " + e);
        }
    }

    public List<Product> getAllProducts(AllProductsFilter filter) {
        ProductsDAO productsDAO = this.getProductsDAO();

        List<Product> products = filter != null ?
                productsDAO.findProductByNameOrDescription(filter.getQuery()) :
                productsDAO.findAllProducts();

        productsDAO.close();
        return products;
    }

    public Product getProduct(ProductFilter filter) {
        ProductsDAO productsDAO = this.getProductsDAO();
        Product product = productsDAO.findProductById(filter.getId());

        productsDAO.close();
        return product;
    }

    public void saveProduct(Product product) {
        ProductsDAO productsDAO = this.getProductsDAO();
        productsDAO.insert(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAvailable()
        );

        productsDAO.close();
    }

}
