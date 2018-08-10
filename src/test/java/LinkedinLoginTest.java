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

    @BeforeMethod
    public void beforeMethod(){
        System.setProperty("webdriver.gecko.driver","/Users/liubomyrned/Downloads/geckodriver");
        browser = new FirefoxDriver();
        browser.get ("https://www.linkedin.com/");

    }
    @AfterMethod
    public void afterMethod(){
        browser.close();
    }

    @Test
    public void successfullLoginTest () throws InterruptedException {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(browser);
        linkedinLoginPage.login("liubomyrned21@gmail.com", "VCM-TuY-EVZ-r4p");
        Thread.sleep(3000);
        String pageTitle = browser.getTitle();
        String pageUrl = browser.getCurrentUrl();
        //WebElement profileNavigationItem = browser.findElement(By.xpath("//*[@id='profile-nav-item']"));
        Thread.sleep(5000);
        Assert.assertEquals(pageTitle, "LinkedIn", "Home page Title is wrong");
        Assert.assertEquals(pageUrl, "https://www.linkedin.com/feed/", "Home page URL is wrong");
        Assert.assertTrue(linkedinLoginPage.isProfileNavigationItemPresent(), "'profileNavigationItem' is not displayed on Home page.");
        Thread.sleep(3000);
        }

    @Test
    public void negativeLoginTest () throws InterruptedException {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(browser);
        linkedinLoginPage.login("tph", "tph2");
        Thread.sleep(3000);
        WebElement alertBox = browser.findElement(By.xpath("//*[@role='alert']"));
        Assert.assertEquals(alertBox.getText(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.", "Alert box has incorrect message");
        Thread.sleep(3000);
    }
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
