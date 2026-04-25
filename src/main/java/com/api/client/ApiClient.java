package com.api.client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiClient {

    public static Response get(RequestSpecification req, String endpoint) {
        return given()
                .spec(req)
                .when()
                .get(endpoint);
    }

    public static Response post(RequestSpecification req, String endpoint, Map<String, ?> body) {
        return given()
                .spec(req)
                .body(body)
                .when()
                .post(endpoint);
    }

    public static Response put(RequestSpecification req, String endpoint, Map<String, ?> body) {
        return given()
                .spec(req)
                .body(body)
                .when()
                .put(endpoint);
    }

    public static Response delete(RequestSpecification req, String endpoint) {
        return given()
                .spec(req)
                .when()
                .delete(endpoint);
    }
}