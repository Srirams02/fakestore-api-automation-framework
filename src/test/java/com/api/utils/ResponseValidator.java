package com.api.utils;

import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseValidator {

    public static void validateStatus(Response response, int expected) {
        Assert.assertEquals(response.getStatusCode(), expected,
                "Status code mismatch");
    }

    public static void validateNotNull(Response response, String key) {
        Assert.assertNotNull(response.jsonPath().get(key),
                key + " is null");
    }

    public static void validateEquals(Response response, String key, Object expected) {
        Assert.assertEquals(response.jsonPath().get(key), expected,
                key + " mismatch");
    }

    public static void validateListNotEmpty(Response response, String key) {
        Assert.assertTrue(response.jsonPath().getList(key).size() > 0,
                key + " list is empty");
    }
}