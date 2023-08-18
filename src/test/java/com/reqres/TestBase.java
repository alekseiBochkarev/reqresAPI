package com.reqres;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    public final int STATUS_CODE_200 = 200;
    public final int STATUS_CODE_201 = 201;
    public final int STATUS_CODE_400 = 400;

    @BeforeAll
    static void configureBeforeAll() {
        //RestAssured.baseURI = "https://reqres.in/";
        //RestAssured.baseURI = "https://act.web-staging.2gis.ru/";
       // RestAssured.baseURI = "https://act.api.2gis.ru";
        RestAssured.baseURI = "https://allure.2gis.dev";
    }

}
