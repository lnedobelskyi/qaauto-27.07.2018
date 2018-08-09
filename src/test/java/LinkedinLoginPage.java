import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinLoginPage {

    WebDriver browser;
    WebElement userEmailField;
    WebElement userPasswordField;
    WebElement signInButton;
    WebElement profileNavigationItem ;


    public LinkedinLoginPage(WebDriver browser){
        this.browser = browser;
        initElements();
    }
    public void initElements(){
        userEmailField = browser.findElement(By.xpath("//*[@id=\"login-email\"]"));
        userPasswordField = browser.findElement(By.xpath("//*[@id=\"login-password\"]"));
        signInButton = browser.findElement(By.xpath("//*[@id=\"login-submit\"]"));

    }

    public void login(String userEmail, String userPass){//это метод который вызывает переменные(либо параметры)
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        signInButton.click();
    }

    public boolean isProfileNavigationItemPresent(){
        profileNavigationItem = browser.findElement(By.xpath("//*[@id='profile-nav-item']"));
        return profileNavigationItem.isDisplayed();
    }
    //Home Task #4:
    //Update successfulLoginTest() to use LinkedinHomePage object.
    //- Create new page object class called LinkedinHomePage.class
    //- Move profileNavidationItem into this new class
    //- Add LinkedinHomePage constructor with browser parameter
    //- Add initElements() method to initialise profoleNavigationItem
    //- Add boolean method isProfileNavigationItemDisplayed() in a new class.
    //- Use new LinkedinHomePage in successfulLoginTest()
    //- Use isProfileNavigationItemDisplayed() method in last Assert.

}
