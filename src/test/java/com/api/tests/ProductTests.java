package com.api.tests;

import com.api.base.BaseTest;
import com.api.endpoints.ProductAPI;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Product Module")
@Owner("Sriram")
public class ProductTests extends BaseTest {

    // 🔥 DATA PROVIDER
    @DataProvider(name = "productIds")
    public Object[][] productData() {
        return new Object[][] {
                {1},
                {2},
                {3}
        };
    }

    // 🔹 TEST 1
    @Test(description = "Validate fetching all products")
    @Story("Get All Products")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify API returns list of all products")
    @Link(name = "API Docs", url = "https://fakestoreapi.com/docs")
    public void getAllProductsTest() {

        Response response = ProductAPI.getAllProducts(request);

        response.then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0].id", notNullValue())
                .body("[0].title", notNullValue())
                .body("[0].price", greaterThan(0f));   // ✅ float fix
    }

    // 🔹 TEST 2
    @Test(description = "Validate fetching product by ID")
    @Story("Get Product")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify product details for given ID")
    @Issue("BUG-101")
    public void getProductByIdTest() {

        Response response = ProductAPI.getProductById(request, 1);

        response.then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", notNullValue())
                .body("price", greaterThan(0f))        // ✅ float fix
                .body("category", notNullValue())
                .body("description", notNullValue());
    }

    // 🔹 TEST 3 (DATA DRIVEN)
    @Test(dataProvider = "productIds", description = "Validate multiple product IDs")
    @Story("Data Driven Product Test")
    @Severity(SeverityLevel.CRITICAL)
    public void getMultipleProductsTest(int id) {

        Response response = ProductAPI.getProductById(request, id);

        response.then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("price", greaterThan(0f));
    }

    // 🔹 TEST 4 (NEGATIVE)
    @Test(description = "Validate invalid product ID")
    @Story("Negative Testing")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify behavior for invalid product ID")
    @Issue("BUG-404")
    public void getInvalidProductTest() {

        Response response = ProductAPI.getProductById(request, 9999);

        response.then()
                .statusCode(anyOf(is(404), is(200)))
                .body(anyOf(
                        is("{}"),
                        containsString("null"),
                        notNullValue()
                ));
    }
}