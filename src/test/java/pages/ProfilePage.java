package pages;



import com.codeborne.selenide.SelenideElement;



import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {

    private SelenideElement mainHeader = $(".main-header"),
            deleteButton = $("#delete-record-undefined"),
            okButton = $("#closeSmallModal-ok"),
            consentButton = $(".fc-consent-root"),
            tableBody = $(".ReactTable");

    public ProfilePage openPage() {
        open("/profile");
        mainHeader.shouldHave(text("Profile"));

        return this;
    }
    public ProfilePage googleConsent() {
        consentButton.$(byText("Consent")).click();

        return this;
    }
    public ProfilePage deleteBook() {
        deleteButton.click();

        return this;
    }
    public ProfilePage confirmDelete() {
        okButton.click();

        return this;
    }


    public ProfilePage checkTableBody() {
        String TEXT = "Git Pocket Guide";
        $(tableBody).shouldNotHave(text(TEXT));

        return this;
    }

}
