package org.ooka.productstore.api;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.ooka.productstore.db.ProductsDAO;
import org.ooka.productstore.graphql.GraphQLRequest;
import org.ooka.productstore.graphql.GraphQLResult;
import org.ooka.productstore.graphql.GraphQLSchemaBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/graphql")
public class GraphQLResource {

    GraphQLSchemaBuilder graphQLSchemaBuilder;

    public GraphQLResource(ProductsDAO productsDAO) {
        graphQLSchemaBuilder = GraphQLSchemaBuilder.getInstance(productsDAO);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response processRequest(@Context HttpServletRequest request) {
        GraphQLResult result = new GraphQLResult();
        GraphQLRequest graphQLRequest;
        try {
            // Parse HTTP body to GraphQLRequest
            graphQLRequest = new GraphQLRequest(request);
        } catch(Exception e) {
            result.setError(e.toString());
            return Response.accepted()
                    .entity(result)
                    .build();
        }

        try {
            // Execute the query and add the data to the GraphQLResult instance
            result.setData(this.executeQuery(graphQLRequest.getQuery()));
        } catch(Exception e) {
            result.setError(e.toString());
            return Response.accepted()
                    .entity(result)
                    .build();
        }

        return Response.ok()
            .entity(result)
            .build();
    }

    private Object executeQuery(String query) {
        GraphQL graphQL = this.graphQLSchemaBuilder.getGraphql();


        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query(query)
                .build();

        ExecutionResult executionResult = graphQL.execute(executionInput);

        return executionResult.getData();
    }
}