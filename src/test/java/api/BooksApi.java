package api;

import models.*;
import java.util.ArrayList;

import static data.BaseURIs.collectionURI;
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
    public static BookCollectionResponse requestBookCollection() { BookCollectionResponse collection =
            given(mainRequest)
                    .when()
                    .get("/BookStore/v1/Books")
                    .then()
                    .spec(bookCollectionResponse)
                    .extract().as(BookCollectionResponse.class);
        return collection;
    }

    public static AddBookResponse addBook(String isb, String token, String userId) {

        ArrayList books = new ArrayList<>();
        books.add(new IsbnBookModel(isb));

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
