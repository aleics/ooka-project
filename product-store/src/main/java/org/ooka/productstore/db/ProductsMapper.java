package org.ooka.productstore.db;

import org.ooka.productstore.core.Product;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductsMapper implements ResultSetMapper<Product> {
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String PRICE_COLUMN = "price";
    private static final String AVAILABLE_COLUMN = "available";

    @Override
    public Product map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Product(
                resultSet.getString(ID_COLUMN),
                resultSet.getString(NAME_COLUMN),
                resultSet.getString(DESCRIPTION_COLUMN),
                resultSet.getFloat(PRICE_COLUMN),
                resultSet.getBoolean(AVAILABLE_COLUMN)
        );
    }
}
