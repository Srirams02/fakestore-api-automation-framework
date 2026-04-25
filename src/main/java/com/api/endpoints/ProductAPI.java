package com.api.endpoints;

import com.api.client.ApiClient;
import io.qameta.allure.Step;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ProductAPI {

    @Step("Get all products")
    public static Response getAllProducts(RequestSpecification req) {

        Response response = ApiClient.get(req, "/products");

        Allure.addAttachment("All Products Response", response.asPrettyString());

        return response;
    }

    @Step("Get product by ID: {id}")
    public static Response getProductById(RequestSpecification req, int id) {

        Response response = ApiClient.get(req, "/products/" + id);

        Allure.addAttachment("Product Response", response.asPrettyString());

        return response;
    }
}