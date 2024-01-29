package api;

import models.*;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static specs.Specifications.*;

public class BooksApi {
    public static void deleteAllBooks(String token, String userId) {
        given(mainRequest)
                .header("Authorization", "Bearer " + token)
                .queryParams("UserId", userId)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(deleteAllBooksResponse);
    }

    public static AddBookResponse addBook(String token, String userId) {

        ArrayList books = new ArrayList<>();
        books.add(new IsbnBookModel("9781449325862"));

        AddBookBodyModel bookData = new AddBookBodyModel();
        bookData.setUserId(userId);
        bookData.setCollectionOfIsbns(books);

        return
                given(mainRequest)
                        .header("Authorization", "Bearer " + token)
                        .body(bookData)
                        .when()
                        .post("/BookStore/v1/Books")
                        .then()
                        .spec(addABookResponse)
                        .extract().as(AddBookResponse.class);
    }
}
