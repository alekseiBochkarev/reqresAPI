package com.reqres;


import com.reqres.pojo.UserDTO;
import com.reqres.pojo.domain.User;
import com.reqres.pojo.domain.UserMapper;
import com.reqres.pojo.remote.UserDataRemote;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;

public class ReqresAPITest extends TestBase {

    public List<Integer> allureIds = Arrays.asList(416100,416103,416142,448931,448934,448936,455309,455310,455310,455311,455312,455313,455314,455315,455315,455315,455316,455317,455318,455319,455320,455321,455322,455323,455324,455324,455326,455328,455328,455329,455330,455331,455332,455334,455336,455336,455336,455337,455338,455340,455341,455342,455343,455343,455347,455349,455648,455649,455650,455651,455652,455653,455655,455656,455657,455658,455659,455660,455661,455662,455663,455664,455665,455665,455665,455665,455666,455667,455668,455669,455670,455671,455672,455673,455674,455675,455676,455677,455678,455679,455680,455681,455682,455683,455684,455685,455686,455687,455688,455689,455690,455691,455692,455693,455694,455695,455696,455697,455698,455699,455700,455701,455702,455703,455704,455705,455706,455707,455708,455709,455710
    );
    @Test
    void allureRestore() {
        JSONObject requestBody = new JSONObject()
                .put("deleted", false);
        //int i = 448405;
        allureIds.forEach(i -> given()
                .accept("*/*")
                .header("authority", "allure.2gis.dev")
                .header("cookie", "ALLURE_TESTOPS_SESSION=b28bcc22-5c67-4751-8665-fbe466c59200; XSRF-TOKEN=3aede4e9-74fb-4c19-8860-9f179fdd47fe")
                .header("origin", "https://allure.2gis.dev")
                .header("x-xsrf-token", "3aede4e9-74fb-4c19-8860-9f179fdd47fe")
                .contentType(JSON)
                .body(requestBody.toString())
                .log().uri()
                .patch("/api/rs/testcase/" + i)
                .then()
                .log().status()
                //.log().body()
                .statusCode(STATUS_CODE_200));
    }


    @Test
    void initHistoryTest() {

        for (int i = 0; i < 320; i++) {
            given()
                    .accept("*/*")
                    .log().uri()
                    .get("api/v1/adjustments/curatorHistoryInit/" + i)
                    .then()
                    .log().status()
                    .statusCode(STATUS_CODE_200);
        }
    }


    @Test
    @DisplayName("Проверка корректного создания пользователя")
    void createUserTest() {
//        JSONObject requestBody = new JSONObject()
//                .put("name", "Oleg")
//                .put("job", "developer");

        UserDTO user = new UserDTO();
        user.name = "Oleg";
        user.job = "developer";


        UserDTO response = given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(user)
                .when()
                .post("/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(STATUS_CODE_201)
                .extract().as(UserDTO.class);

        Assertions.assertEquals(user.name, response.name);
    }

    @Test
    @DisplayName("Получение пользователя с id = 2")
    void getUserTest() {

        UserDataRemote response = given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .when()
                .get("/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(STATUS_CODE_200)
                .extract().as(UserDataRemote.class);

        User user = UserMapper.map(response.data);

        Assertions.assertEquals(user.contact.email, response.data.email);
    }

    @Test
    @DisplayName("Проверка успешной авторизации пользователя")
    void loginSuccessfulUserTest() {
        JSONObject requestBody = new JSONObject()
                .put("email", "eve.holt@reqres.in")
                .put("password", "cityslicka");

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody.toString())
                .when()
                .post("/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(STATUS_CODE_200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    @DisplayName("Проверка неуспешной авторизации пользователя")
    void loginUnsuccessfulUserTest() {
        JSONObject requestBody = new JSONObject()
                .put("email", "peter@klaven");

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody.toString())
                .when()
                .post("/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(STATUS_CODE_400)
                .body("error", is("Missing password"));
    }

    @Test
    @DisplayName("Проверка успешной регистрации пользователя")
    void registerSuccessfulUserTest() {
        JSONObject requestBody = new JSONObject()
                .put("email", "eve.holt@reqres.in")
                .put("password", "pistol");

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody.toString())
                .when()
                .post("/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(STATUS_CODE_200)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    @DisplayName("Проверка неуспешной регистрации пользователя")
    void registerUnsuccessfulUserTest() {
        JSONObject requestBody = new JSONObject()
                .put("email", "sydney@fife");

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody.toString())
                .when()
                .post("/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(STATUS_CODE_400)
                .body("error", is("Missing password"));
    }

}
