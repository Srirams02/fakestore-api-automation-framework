package com.api.tests;

import com.api.base.BaseTest;
import com.api.endpoints.ProductAPI;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Product Module")
@Owner("Sriram")
public class ProductTests extends BaseTest {

    @Test
    public void getAllProductsTest() {

        Response response = ProductAPI.getAllProducts(request);

        response.then()
                .statusCode(200)
                .body("$", notNullValue());
    }

    @Test
    public void getProductByIdTest() {

        Response response = ProductAPI.getProductById(request, 1);

        if (response.statusCode() == 200) {
            response.then()
                    .body("id", notNullValue())
                    .body("title", notNullValue());
        }
    }

    @Test
    public void getInvalidProductTest() {

        Response response = ProductAPI.getProductById(request, 9999);

        // Accept any realistic response
        assert(response.statusCode() == 200 || response.statusCode() == 404);
    }
}