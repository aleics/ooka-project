package com.senacor.code.fullstack.chat.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.net.HttpURLConnection;
import java.net.URL;

public class AuthService {
    private static String endpointURL = "http://local.store.com/usermngmt/api/v1/auth";

    public HttpStatus authenticate(String token) {
        return makeRequest(HttpMethod.POST, token);
    }

    private HttpStatus makeRequest(HttpMethod method, String token) {
        try {
            URL url = new URL(endpointURL);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod(String.valueOf(method));
            connection.setRequestProperty(HttpHeaders.AUTHORIZATION, token);
            connection.connect();

            int statusCode = connection.getResponseCode();

            return HttpStatus.resolve(statusCode);

        } catch (Exception e) {
            System.out.println("ERROR: AuthService couldn't make a request. Exception: " + e.getMessage());
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
