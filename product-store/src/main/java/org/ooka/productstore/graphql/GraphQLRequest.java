package org.ooka.productstore.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.StringWriter;


public class GraphQLRequest {
    String query = null;

    public GraphQLRequest() {
    }

    public GraphQLRequest(HttpServletRequest httpServletRequest) throws java.io.IOException {
        GraphQLRequestHelper helper = new GraphQLRequestHelper();
        GraphQLRequest request = helper.buildQuery(httpServletRequest);

        this.query = request.getQuery();
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    class GraphQLRequestHelper {

        protected final ObjectMapper mapper = new ObjectMapper();

        GraphQLRequest buildQuery(HttpServletRequest httpServletRequest) throws java.io.IOException {
            String body = readBody(httpServletRequest);
            return extractQuery(body);
        }

        GraphQLRequest extractQuery(String body) throws java.io.IOException {
            if (body == null) {
                return null;
            }
            return mapper.readValue(body, GraphQLRequest.class);
        }

        private String readBody(HttpServletRequest request) throws java.io.IOException {
            InputStream inputStream = request.getInputStream();

            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer);

            return writer.toString();
        }
    }
}
