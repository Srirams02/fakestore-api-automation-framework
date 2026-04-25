package com.api.tests;

import com.api.base.BaseTest;
import com.api.endpoints.AuthAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthTests extends BaseTest {

    @Test
    public void loginTest() {

        Response response = AuthAPI.login(request);

        response.then().statusCode(201); // IMPORTANT FIX

        Assert.assertNotNull(response.jsonPath().getString("token"));
    }
}