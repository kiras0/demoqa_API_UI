package pages;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {
    private SelenideElement mainHeader = $(".main-header"),
            deleteButton = $("#delete-record-undefined"),
            okButton = $("#closeSmallModal-ok"),
            consentButton = $(".fc-consent-root"),
            tableBody = $(".rt-tbody");

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
        $(tableBody).shouldBe(empty);

        return this;
    }

}
