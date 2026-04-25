package com.api.endpoints;

import com.api.client.ApiClient;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class AuthAPI {

    @Step("Login with username: {username}")
    public static Response login(RequestSpecification req, String username, String password) {

        Map<String, String> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);

        Allure.addAttachment("Login Request", body.toString());

        Response response = ApiClient.post(req, "/auth/login", body);

        Allure.addAttachment("Login Response", response.asPrettyString());

        return response;
    }

    // convenience method (uses default creds)
    public static Response login(RequestSpecification req) {
        return login(req, "mor_2314", "83r5^_");
    }
}