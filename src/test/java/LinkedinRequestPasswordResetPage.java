import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

public class LinkedinRequestPasswordResetPage extends BasePage {

    @FindBy(xpath = "//input[@name='userName']")
    private WebElement userEmailField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;


    public LinkedinRequestPasswordResetPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);

    }

    public boolean isLoaded() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return findAccountButton.isDisplayed()
                && getCurrentPageTitle().equals("Reset Password | LinkedIn")
                && getCurrentPageUrl().contains("uas/request-password-reset");
    }

    public LinkedinPasswordResetSubmitPage findAccount(String userEmail) {
        gMailService = new GMailService (userEmail, "G2u-Bn7-Sa8-9xb");
        gMailService.connect();

        userEmailField.sendKeys(userEmail);
        findAccountButton.click();
        String messageSubject = "here's the link to reset your password";
        String messageTo = "liubomyrned21@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 240);
        System.out.println("Conect: " + message);
        return new LinkedinPasswordResetSubmitPage(browser);
    }

}
