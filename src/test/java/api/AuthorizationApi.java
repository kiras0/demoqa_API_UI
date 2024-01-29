package api;

import models.LoginBodyModel;
import models.LoginResponse;

import static data.UserData.PASSWORD;
import static data.UserData.USERNAME;
import static io.restassured.RestAssured.given;
import static specs.Specifications.mainRequest;
import static specs.Specifications.successfulLoginResponse;

public class AuthorizationApi {
    public static LoginResponse authResponse(){
        LoginBodyModel userRegister = new LoginBodyModel();
        userRegister.setUserName(USERNAME);
        userRegister.setPassword(PASSWORD);

        return
                given(mainRequest)
                        .body(userRegister)
                        .when()
                        .post("/Account/v1/Login")
                        .then()
                        .spec(successfulLoginResponse)
                        .extract().as(LoginResponse.class);

    }
}
