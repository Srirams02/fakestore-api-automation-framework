package com.api.endpoints;

import com.api.client.ApiClient;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.*;

public class CartAPI {

    public static Response createCart(RequestSpecification req) {

        Map<String, Object> body = new HashMap<>();

        body.put("userId", 1);
        body.put("date", "2020-02-03");

        List<Map<String, Object>> products = new ArrayList<>();

        Map<String, Object> product = new HashMap<>();
        product.put("productId", 1);
        product.put("quantity", 2);

        products.add(product);

        body.put("products", products);

        return ApiClient.post(req, "/carts", body);
    }

    public static Response getCart(RequestSpecification req, int id) {
        return ApiClient.get(req, "/carts/" + id);
    }
}