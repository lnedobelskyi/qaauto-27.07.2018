package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinSubmissionResetPasswordPage extends BasePage {
    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement submitButton;

    public LinkedinSubmissionResetPasswordPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public boolean isLoaded() {
        //Fixme
        return submitButton.isDisplayed()
                && getCurrentPageTitle().equals("Reset Your Password | LinkedIn")
                && getCurrentPageUrl().contains("checkpoint/rp/password-reset?requestSubmissionId");
    }
}
