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
        WebElement profileNavigationItem = browser.findElement(By.xpath("//*[@id='profile-nav-item']"));
        Assert.assertEquals(pageTitle, "LinkedIn", "Home page Title is wrong");

        Assert.assertEquals(pageUrl, "https://www.linkedin.com/feed/", "Home page URL is wrong");

        Assert.assertTrue(profileNavigationItem.isDisplayed(), "'profileNavigationItem' is not displayed on Home page.");

        Thread.sleep(3000);

    }
    @Test
    public void negativeLoginTest () throws InterruptedException {
        WebElement userEmailField = browser.findElement(By.xpath("//*[@id=\"login-email\"]"));
        WebElement userPasswordField = browser.findElement(By.xpath("//*[@id=\"login-password\"]"));
        WebElement signInButton = browser.findElement(By.xpath("//*[@id=\"login-submit\"]"));
        userEmailField.sendKeys("tph");
        userPasswordField.sendKeys("tph2");
        signInButton.click();

        Thread.sleep(3000);

        WebElement alertBox = browser.findElement(By.xpath("//*[@role='alert']"));
        Assert.assertEquals(alertBox.getText(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.", "Alert box has incorrect message");
        Thread.sleep(3000);


    }
}
