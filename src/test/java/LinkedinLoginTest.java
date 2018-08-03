import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LinkedinLoginTest {


    @Test
    public void successfullLoginTest (){

    }
    @Test
    public void negativeLoginTest () throws InterruptedException {
        System.setProperty("webdriver.gecko.driver","/Users/liubomyrned/Downloads/geckodriver");
        WebDriver browser = new FirefoxDriver();
        browser.get ("https://www.linkedin.com/");
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
        browser.close();
    }
}
