package api;

import models.*;
import java.util.ArrayList;

import static data.BaseURIs.collectionURI;
import static data.BookData.bookIsb;
import static io.restassured.RestAssured.given;
import static specs.Specifications.*;

public class BooksApi {
    public static void deleteAllBooks(String token, String userId) {
        given(mainRequest)
                .header("Authorization", "Bearer " + token)
                .queryParams("UserId", userId)
                .when()
                .delete(collectionURI)
                .then()
                .spec(deleteAllBooksResponse);
    }
    public static AddBookResponse addBook(String token, String userId) {

        ArrayList books = new ArrayList<>();
        books.add(new IsbnBookModel(bookIsb));

        AddBookBodyModel bookData = new AddBookBodyModel();
        bookData.setUserId(userId);
        bookData.setCollectionOfIsbns(books);
        return
                given(mainRequest)
                        .header("Authorization", "Bearer " + token)
                        .body(bookData)
                        .when()
                        .post(collectionURI)
                        .then()
                        .spec(addABookResponse)
                        .extract().as(AddBookResponse.class);
    }
}
