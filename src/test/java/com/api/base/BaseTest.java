package com.api.base;

import com.api.endpoints.AuthAPI;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    protected RequestSpecification request;
    protected Properties prop;
    protected String token;

    @BeforeMethod
    public void setup() throws IOException {

        // Load config
        prop = new Properties();
        prop.load(new FileInputStream("src/test/resources/config.properties"));

        // Base request
        request = new RequestSpecBuilder()
                .setBaseUri(prop.getProperty("baseUrl"))
                .setContentType(ContentType.JSON)
                .build();

        // Login call
        Response loginResponse = AuthAPI.login(request);

        // Debug response (useful for CI)
        System.out.println("===== LOGIN RESPONSE =====");
        loginResponse.prettyPrint();

        // Safe token handling
        try {
            if (loginResponse != null &&
                loginResponse.statusCode() == 200 &&
                loginResponse.getContentType() != null &&
                loginResponse.getContentType().contains("json")) {

                token = loginResponse.jsonPath().getString("token");

                if (token != null && !token.isEmpty()) {
                    request.header("Authorization", "Bearer " + token);
                    System.out.println("Token extracted successfully");
                } else {
                    System.out.println("Token is null or empty");
                }

            } else {
                System.out.println("Login failed or returned non-JSON response");
            }

        } catch (Exception e) {
            System.out.println("Error extracting token: " + e.getMessage());
        }
    }
}