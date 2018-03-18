package org.ooka.productstore.security;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.net.URL;

public class AuthService {
    private static String endpointURL = "http://local.store.com/usermngmt/api/v1/auth";

    public Response.Status authenticate(String token) {
        return makeRequest(HttpMethod.POST, token);
    }

    private Response.Status makeRequest(String method, String token) {
        try {
            URL url = new URL(endpointURL);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod(method);
            connection.setRequestProperty(HttpHeaders.AUTHORIZATION, token);
            connection.connect();

            int statusCode = connection.getResponseCode();

            return Response.Status.fromStatusCode(statusCode);

        } catch (Exception e) {
            System.out.println("ERROR: AuthService couldn't make a request. Exception: " + e.getMessage());
            return Response.Status.INTERNAL_SERVER_ERROR;
        }
    }
}
