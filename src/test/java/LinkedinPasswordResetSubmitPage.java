import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinPasswordResetSubmitPage extends BasePage {

    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;


    public LinkedinPasswordResetSubmitPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);

    }

    public boolean isLoaded() {
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resendLinkButton.isDisplayed()
                && getCurrentPageTitle().contains("Please check your mail for reset password link.")
                && getCurrentPageUrl().contains("request-password-reset-submit");
    }
    public LinkedinSetNewPasswordPage navigateToLinkFromEmail() {
       //ToDo
        return new LinkedinSetNewPasswordPage(browser);
    }
}
