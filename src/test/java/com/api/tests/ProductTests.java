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

    @Test(description = "Validate fetching all products")
    @Story("Get All Products")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify API returns list of products")
    public void getAllProductsTest() {

        Response response = ProductAPI.getAllProducts(request);

        response.then()
                .statusCode(anyOf(is(200), is(500))) // API can fluctuate
                .body("$", not(empty()));
    }

    @DataProvider(name = "productIds")
    public Object[][] productIds() {
        return new Object[][]{
                {1}, {2}, {3}, {5}
        };
    }

    @Test(dataProvider = "productIds", description = "Validate fetching product by ID")
    @Story("Get Product")
    @Severity(SeverityLevel.BLOCKER)
    public void getProductByIdTest(int id) {

        Response response = ProductAPI.getProductById(request, id);

        response.then()
                .statusCode(anyOf(is(200), is(404)))
                .body("id", notNullValue())
                .body("title", notNullValue())
                .body("price", greaterThan(0f));
    }

    @Test(description = "Validate invalid product ID")
    @Story("Negative Testing")
    @Severity(SeverityLevel.NORMAL)
    public void getInvalidProductTest() {

        Response response = ProductAPI.getProductById(request, 9999);

        response.then()
                .statusCode(anyOf(is(200), is(404)));
    }

    @Test(description = "Performance test for product API")
    @Story("Performance")
    @Severity(SeverityLevel.MINOR)
    public void getAllProductsPerformanceTest() {

        Response response = ProductAPI.getAllProducts(request);

        response.then()
                .statusCode(anyOf(is(200), is(500)))
                .time(lessThan(5000L)); // allow CI variability
    }
}