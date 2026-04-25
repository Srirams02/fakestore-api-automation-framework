package com.api.utils;

import io.qameta.allure.Allure;

public class AllureAttachment {

    public static void attachResponse(String name, String body) {
        Allure.addAttachment(name, "application/json", body);
    }
}