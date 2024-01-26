package test;

import io.restassured.response.Response;
import models.LoginBodyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class LoginTest extends TestBase{


    @Test
    void successfulLoginUITest() {
        open("/login");

    }

    @Test
    @DisplayName("User login")
    void successfulRegistration() {

        LoginBodyModel userRegister = new LoginBodyModel();
        userRegister.setUserName("hello123");
        userRegister.setPassword("World123@");

        Response authResponse = step("User Login", ()->
                given()
                .filter(withCustomTemplates())
                .body(userRegister)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("/Account/v1/Login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().response()
        );
        step("Import user cookies", ()->{
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.path("userId")));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.path("token")));}
);
        step("Open profile", ()->
        open("/profile")
        );
    }
}
