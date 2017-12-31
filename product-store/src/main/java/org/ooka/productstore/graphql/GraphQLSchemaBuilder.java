package org.ooka.productstore.graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.ooka.productstore.core.ProductRepository;
import org.ooka.productstore.core.Query;

public class GraphQLSchemaBuilder {
    private static GraphQLSchemaBuilder instance;
    private GraphQLSchema schema;
    private GraphQL graphql;

    private GraphQLSchemaBuilder() {
        ProductRepository productRepository = new ProductRepository();
        schema = SchemaParser.newParser()
                .file("schema/schema.graphqls")
                .resolvers(new Query(productRepository))
                .build()
                .makeExecutableSchema();

        graphql = GraphQL.newGraphQL(schema)
                .build();
    }

    public static GraphQLSchemaBuilder getInstance() {
        if (instance == null) {
            instance = new GraphQLSchemaBuilder();
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
