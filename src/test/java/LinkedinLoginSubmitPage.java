import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinLoginSubmitPage {

    private WebDriver browser;

    @FindBy(xpath="//*[@role='alert']")
    private WebElement alertBox;

    @FindBy(xpath="//span[@id='session_key-login-error']")
    private WebElement userEmailValidationText;

    @FindBy(xpath="//span[@id='session_password-login-error']")
    private WebElement userPasswordValidationText;

    public LinkedinLoginSubmitPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);

    }

    public String getAlertBoxText(){
        return alertBox.getText();

    }
    public String getCurrentPageTitle(){
        return browser.getTitle();

    }

    public String getCurrentPageUrl(){
        return browser.getCurrentUrl();

    }

    public boolean isLoaded() {
        return isAlertBoxDisplayed()
                 && isTitleValid()
                 && isCurrentURLValid();
    }

    public boolean isAlertBoxDisplayed(){
        return alertBox.isDisplayed();
    }

    public boolean isTitleValid(){
       return getCurrentPageTitle().contains("Войти в LinkedIn");
    }

    public boolean isCurrentURLValid(){
        return getCurrentPageUrl().contains("/uas/login-submit");
    }

    public String getUserEmailValidationText(){
        return userEmailValidationText.getText();
    }

    public String getPasswordValidationText() {
        return userPasswordValidationText.getText();
    }

    public String getUserPasswordValidationText() {return userPasswordValidationText.getText();
    }
}
