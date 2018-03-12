package org.ooka.productstore.graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.ooka.productstore.core.Product;
import org.ooka.productstore.core.ProductsRepository;
import org.ooka.productstore.core.Query;
import org.ooka.productstore.db.ProductsDAO;

public class GraphQLSchemaBuilder {
    private static GraphQLSchemaBuilder instance;
    private GraphQLSchema schema;
    private GraphQL graphql;

    private GraphQLSchemaBuilder(ProductsDAO productsDAO) {
        ProductsRepository productsRepository = new ProductsRepository(productsDAO);
        schema = SchemaParser.newParser()
                .file("schema/schema.graphqls")
                .resolvers(new Query(productsRepository))
                .build()
                .makeExecutableSchema();

        graphql = GraphQL.newGraphQL(schema)
                .build();
    }

    public static GraphQLSchemaBuilder getInstance(ProductsDAO productsDAO) {
        if (instance == null) {
            instance = new GraphQLSchemaBuilder(productsDAO);
        }
        return instance;
    }

    public GraphQLSchema getSchema() {
        return schema;
    }

    public GraphQL getGraphql() {
        return graphql;
    }
}
