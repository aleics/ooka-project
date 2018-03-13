package org.ooka.productstore.db;

import org.ooka.productstore.core.Product;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface ProductsDAO {
    @SqlUpdate("create table products (id varchar(100) primary key, name varchar(100), description varchar(250), price decimal, available boolean)")
    void createProductsTable();

    @SqlUpdate("insert into products (id, name, description, price, available) values (:id, :name, :description, :price, :available)")
    void insert(
            @Bind("id") String id,
            @Bind("name") String name,
            @Bind("description") String description,
            @Bind("price") Float price,
            @Bind("available") Boolean available
    );

    @Mapper(ProductsMapper.class)
    @SqlQuery("select * from products")
    List<Product> findAllProducts();

    @Mapper(ProductsMapper.class)
    @SqlQuery("select * from products where id = :id")
    Product findProductById(@Bind("id") String id);

    @Mapper(ProductsMapper.class)
    @SqlQuery("select * from products where name ilike '%' || :q || '%' or description ilike '%' || :q || '%'")
    List<Product> findProductByNameOrDescription(@Bind("q") String q);

    void close();
}
