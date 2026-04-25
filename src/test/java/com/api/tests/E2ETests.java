package com.api.tests;

import com.api.base.BaseTest;
import com.api.endpoints.AuthAPI;
import com.api.endpoints.CartAPI;
import com.api.endpoints.ProductAPI;
import com.api.utils.AllureAttachment;
import com.api.utils.AllureLogger;
import com.api.utils.ResponseValidator;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

@Epic("FakeStore API")
@Feature("E2E Flow")
public class E2ETests extends BaseTest {

    @Test
    @Description("Complete flow: Login → Product → Cart")
    @Severity(SeverityLevel.BLOCKER)
    public void completeFlowTest() {

        AllureLogger.logStep("Login API");
        Response loginResponse = AuthAPI.login(request);
        AllureAttachment.attachResponse("Login Response", loginResponse.asPrettyString());

        ResponseValidator.validateStatus(loginResponse, 201);
        ResponseValidator.validateNotNull(loginResponse, "token");

        AllureLogger.logStep("Get Product");
        Response productResponse = ProductAPI.getProductById(request, 1);
        AllureAttachment.attachResponse("Product Response", productResponse.asPrettyString());

        ResponseValidator.validateStatus(productResponse, 200);
        ResponseValidator.validateEquals(productResponse, "id", 1);

        AllureLogger.logStep("Create Cart");
        Response cartResponse = CartAPI.createCart(request);
        AllureAttachment.attachResponse("Cart Response", cartResponse.asPrettyString());

        ResponseValidator.validateStatus(cartResponse, 201);

        int cartId = cartResponse.jsonPath().getInt("id");

        AllureLogger.logStep("Fetch Cart");
        Response getCart = CartAPI.getCart(request, cartId);
        AllureAttachment.attachResponse("Get Cart Response", getCart.asPrettyString());

        ResponseValidator.validateStatus(getCart, 200);
    }
}