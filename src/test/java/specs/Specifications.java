package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class Specifications {
    public static RequestSpecification mainRequest = with()
        .filter(withCustomTemplates())
            .contentType(JSON)
            .log().headers()
            .log().body()
            .log().uri();

    public static ResponseSpecification successfulLoginResponse = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();
    public static ResponseSpecification addABookResponse = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(ALL)
            .build();
    public static ResponseSpecification deleteAllBooksResponse = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(ALL)
            .build();


}
