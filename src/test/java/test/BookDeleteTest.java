package test;

import api.AuthorizationApi;
import api.BooksApi;
import helpers.WithLogin;
import models.LoginResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static io.qameta.allure.Allure.step;



public class BookDeleteTest extends TestBase{
   ProfilePage profilePage = new ProfilePage();
    @Test
    @Tag("ApiAndUi")
    @DisplayName("Deleting book from Demoqa using API and UI")
    @WithLogin
    void successfulRegistration() {

    LoginResponse authResponse =
        step("API Login request", AuthorizationApi::authResponse
        );


        step("Delete all Books through API", () ->
               BooksApi.deleteAllBooks(authResponse.getToken(), authResponse.getUserId())
        );


        step("Add book to the Collection using API", () ->
               BooksApi.addBook(authResponse.getToken(), authResponse.getUserId())
        );

        
        step("Open profile and data consent", () ->

            profilePage.googleConsent()
                       .openPage()


        );


        step("Delete book from collection through UI", () ->
            profilePage.deleteBook()

        );

         step("Confirm Deletion of book", () ->
             profilePage.confirmDelete()
                       
         );
                 step("Check that the collection is empty.", () ->
             profilePage.checkTableBody()
        );
    }
}
