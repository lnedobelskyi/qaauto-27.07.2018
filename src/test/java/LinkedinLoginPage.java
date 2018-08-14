import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinLoginPage {

    private WebDriver browser;
    private WebElement userEmailField;
    private WebElement userPasswordField;
    private WebElement signInButton;
    private WebElement signInButtonIsDisalbed;
    //private WebElement profileNavigationItem ;


    public LinkedinLoginPage(WebDriver browser) {
        this.browser = browser;
        initElements();
    }

    private void initElements() {
        userEmailField = browser.findElement(By.xpath("//*[@id=\"login-email\"]"));
        userPasswordField = browser.findElement(By.xpath("//*[@id=\"login-password\"]"));
        signInButton = browser.findElement(By.xpath("//*[@id=\"login-submit\"]"));
        signInButtonIsDisalbed = browser.findElement(By.xpath("//*[@id=\"login-submit\"]"));

    }

    public void login(String userEmail, String userPass) {//это метод который вызывает переменные(либо параметры)
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        signInButton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean SignInButtonIsDisabled() {
        return signInButton.isEnabled();
    }
    public String getCurrentPageTitle (){ return browser.getTitle();}
    public String getCurrentUrl () {return browser.getCurrentUrl();}

    public boolean isLoaded(){
        return userEmailField.isDisplayed() &&
                getCurrentPageTitle().contains("LinkedIn: Login or Sign Up");
    }

}
