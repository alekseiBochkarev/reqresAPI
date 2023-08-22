package com.reqres;

import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    public final int STATUS_CODE_200 = 200;
    public final int STATUS_CODE_201 = 201;
    public final int STATUS_CODE_400 = 400;
    public static final String APPLICATION_URL = "https://reqres.in";

    @BeforeAll
    static void configureBeforeAll() {
        //RestAssured.baseURI = "https://reqres.in/";
        //RestAssured.baseURI = "https://act.web-staging.2gis.ru/";
       // RestAssured.baseURI = "https://act.api.2gis.ru";
       // RestAssured.baseURI = "https://allure.2gis.dev";
    }

    protected static String getInitAuditUrl() {
        return APPLICATION_URL + "/api/v1.0/init/";
    }

}
