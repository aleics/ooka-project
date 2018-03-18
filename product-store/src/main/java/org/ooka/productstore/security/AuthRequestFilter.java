package org.ooka.productstore.security;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthRequestFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String token = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (token != null && !token.isEmpty()) {
            AuthService authService = new AuthService();
            Response.Status status = authService.authenticate(token);
            if (!status.equals(Response.Status.OK)) {
                abortRequest(containerRequestContext, status);
            }
        } else {
            abortRequest(containerRequestContext, Response.Status.FORBIDDEN);
        }
    }

    private void abortRequest(ContainerRequestContext containerRequestContext, Response.Status status) {
        containerRequestContext.abortWith(
            Response.status(status).build()
        );
    }
}
