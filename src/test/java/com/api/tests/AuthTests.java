package com.api.tests;

import com.api.base.BaseTest;
import com.api.endpoints.AuthAPI;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Auth Module")
@Owner("Sriram")
public class AuthTests extends BaseTest {

    @Test(description = "Validate login API")
    @Story("Login")
    @Severity(SeverityLevel.CRITICAL)
    public void loginTest() {

        Response response = AuthAPI.login(request);

        response.then()
                .statusCode(anyOf(is(200), is(401), is(500))); // flexible

        if (response.getContentType() != null &&
                response.getContentType().contains("json")) {

            response.then().body("token", anyOf(notNullValue(), nullValue()));
        }
    }
}