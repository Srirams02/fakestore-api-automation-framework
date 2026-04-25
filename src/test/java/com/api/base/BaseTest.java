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

        // 🔹 Load config
        prop = new Properties();
        prop.load(new FileInputStream("src/test/resources/config.properties"));

        // 🔹 Base request (no auth yet)
        request = new RequestSpecBuilder()
                .setBaseUri(prop.getProperty("baseUrl"))
                .setContentType(ContentType.JSON)
                .build();

        // LOGIN
        Response loginResponse = AuthAPI.login(request);

        token = loginResponse.jsonPath().getString("token");

        // (optional debug)
        System.out.println("TOKEN: " + token);

        // ADD TOKEN TO HEADER (reused by all APIs)
        request = new RequestSpecBuilder()
                .setBaseUri(prop.getProperty("baseUrl"))
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }
}