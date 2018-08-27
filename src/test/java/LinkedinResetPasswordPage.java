import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinResetPasswordPage extends BasePage {

    @FindBy(xpath = "//*[@id=nonfastrack]")
    private WebElement UserEmailField;

    @FindBy(xpath = "//*[@id='app__container]/div[1]/header")
    private WebElement CurrentPageTitle;

    @FindBy(xpath = "//*[@id=reset-password-submit-button]")
    private WebElement userResetPasswordButton;

    public LinkedinResetPasswordPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);

    }

    public boolean isLoaded() {
        return resetPasswordPage.isDisplayed()
                && getCurrentPageTitle().contains("First, let's find your account")
                && getCurrentPageUrl().contains("/uas/request-password-reset?trk=uno-reg-guest-home-forgot-password");
    }

    public int getSearchResultsCount() {
        return resetPasswordPage.size();
    }
}
