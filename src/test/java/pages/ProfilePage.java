package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static data.BaseURIs.profileURI;
import static data.BookData.pocketGuideTitle;

public class ProfilePage {
    private SelenideElement mainHeader = $(".main-header"),
            deleteButton = $("#delete-record-undefined"),
            okButton = $("#closeSmallModal-ok"),
            consentBanner = $(".fc-consent-root"),
            emptyRows = $(".rt-noData"),
            tableBody = $(".ReactTable");
    public ProfilePage openPage() {
        open(profileURI);
        mainHeader.shouldHave(text("Profile"));
        return this;
    }
    public ProfilePage googleConsent() {
        if (consentBanner.isDisplayed()) {
            consentBanner.$(byText("Consent")).click();
        }
        else{
            System.out.println("No consent window");
        }
        return this;
    }

    public ProfilePage checkForBook() {
        emptyRows.shouldNotBe(visible);
        return this;
    }
    public ProfilePage deleteBook() {
        deleteButton.click();
        return this;
    }
    public ProfilePage confirmDelete() {
        okButton.click();
        Selenide.confirm();
        return this;
    }
    public ProfilePage checkTableBody() {
        tableBody.shouldNotHave(text(pocketGuideTitle));
        return this;
    }

}
