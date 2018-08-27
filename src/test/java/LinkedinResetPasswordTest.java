import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class LinkedinResetPasswordTest {
    WebDriver browser;
    LinkedinLoginPage linkedinLoginPage;

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.gecko.driver","/Users/liubomyrned/Downloads/geckodriver");
        browser = new FirefoxDriver();
        browser.get("https://www.linkedin.com/");
        linkedinLoginPage = new LinkedinLoginPage(browser);
        }

    @AfterMethod
    public void afterMethod() {
        browser.close();
    }


}


/*Home Task #8:
        Add test for Reset Password
        - explore Reset Password scenario manually before automation
        - create test scenario with all necessary page objects
        - Put sleep for few minutes in place where test should get password recovery link from email (While test is sleeping you'll need to get a link from email manually and navigate to that link in Browser that was opened by test so that after sleep Test could proceed with next steps)
        - You test scenario should end up logged in with new password on Home page

        Note: Use PageObject and PageFactory patterns. Avoid using any bad practices.*/