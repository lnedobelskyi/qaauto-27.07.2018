import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LinkedinLoginTest {
    WebDriver browser;
    LinkedinLoginPage linkedinLoginPage;

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.gecko.driver", "/Users/liubomyrned/Downloads/geckodriver");
        browser = new FirefoxDriver();
        browser.get("https://www.linkedin.com/");
        linkedinLoginPage = new LinkedinLoginPage(browser);

    }
    @AfterMethod
    public void afterMethod(){
        browser.close();
    }

    @Test
    public void successfullLoginTest () {
        linkedinLoginPage.login("liubomyrned21@gmail.com", "VCM-TuY-EVZ-r4p");
        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(browser);
        Assert.assertTrue(linkedinHomePage.isLoaded(),"Home page is loaded.");
        }

    @Test
    public void negativeLoginTest () {
        linkedinLoginPage.login("tph", "tph2");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);
        Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.", "Alert box has incorrect message");

    }
}
