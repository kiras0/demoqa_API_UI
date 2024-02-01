package test;

import api.AuthorizationApi;
import api.BooksApi;
import helpers.WithLogin;
import models.BookCollectionResponse;
import models.LoginResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;
import static io.qameta.allure.Allure.step;

public class BookDeleteTest extends TestBase{
    private final int BOOK_NO = 0;
   ProfilePage profilePage = new ProfilePage();

    @Test
    @Tag("ApiAndUi")
    @DisplayName("Deleting book from Demoqa using API and UI")
    @WithLogin
    void deletingBookUsingApiAndUi() {
// Extracting collection of books
        BookCollectionResponse collection = BooksApi.requestBookCollection();

        LoginResponse authResponse =
        step("API Login request", AuthorizationApi::authResponse
        );
        step("Delete all Books through API", () ->
               BooksApi.deleteAllBooks(authResponse.getToken(), authResponse.getUserId())
        );

        step("Add book to the Collection using API", () ->
                BooksApi.addBook(collection.getBooks()[BOOK_NO].getIsbn(), authResponse.getToken(), authResponse.getUserId())
        );
        step("Data consent and opening profile", () ->
             profilePage.googleConsent()
                        .openPage()
        );
        step("Check that the colection is not empty", () ->
              profilePage.checkForBook()
        );
        step("Delete book from collection through UI", () ->
             profilePage.deleteBook()
        );
        step("Confirm Deletion of book", () ->
             profilePage.confirmDelete()
        );
        step("Check that the collection is empty.", () ->
             profilePage.checkTableBody(collection.getBooks()[BOOK_NO].getTitle())
        );
    }
}
