package org.ooka.productstore.graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.ooka.productstore.core.ProductsRepository;
import org.skife.jdbi.v2.DBI;

public class GraphQLSchemaBuilder {
    private static GraphQLSchemaBuilder instance;
    private GraphQLSchema schema;
    private GraphQL graphql;

    private GraphQLSchemaBuilder(DBI jdbi) {
        ProductsRepository productsRepository = new ProductsRepository(jdbi);
        schema = SchemaParser.newParser()
                .file("schema/schema.graphqls")
                .resolvers(new Query(productsRepository), new Mutation(productsRepository))
                .build()
                .makeExecutableSchema();

        graphql = GraphQL.newGraphQL(schema)
                .build();
    }

    public static GraphQLSchemaBuilder getInstance(DBI jdbi) {
        if (instance == null) {
            instance = new GraphQLSchemaBuilder(jdbi);
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
