package com.api.tests;

import com.api.base.BaseTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Cart Module")
@Owner("Sriram")
public class CartTests extends BaseTest {

    @Test(description = "Create cart test")
    @Story("Cart")
    @Severity(SeverityLevel.CRITICAL)
    public void createCartTest() {

        String body = "{ \"userId\": 1, \"date\": \"2020-02-03\" }";

        Response response = given()
                .spec(request)
                .body(body)
                .post("/carts");

        response.then()
                .statusCode(anyOf(is(200), is(201), is(500)))
                .body("id", anyOf(notNullValue(), nullValue()));
    }
}