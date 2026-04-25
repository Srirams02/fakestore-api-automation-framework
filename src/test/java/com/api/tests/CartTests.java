package com.api.tests;

import com.api.base.BaseTest;
import com.api.endpoints.CartAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {

    @Test
    public void createCartTest() {

        Response response = CartAPI.createCart(request);

        response.then().statusCode(201); // IMPORTANT FIX

        Assert.assertTrue(response.jsonPath().getInt("id") > 0);
    }
}