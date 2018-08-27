import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LinkedinLoginTest {
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

    @DataProvider
    public Object[][] validFieldsCombination() {
        return new Object[][]{
                {"liubomyrned21@gmail.com", "VCM-TuY-EVZ-r4p"},
                {"Liubomyrned21@gmail.com", "VCM-TuY-EVZ-r4p"},
        };
    }


    @Test(dataProvider = "validFieldsCombination")
    public void successFullLoginTest (String userEmail, String userPass){

        LinkedinHomePage linkedinHomePage = linkedinLoginPage.loginReturnHomePage(userEmail, userPass);
        Assert.assertTrue(linkedinHomePage.isLoaded(), "Home page is not loaded");
    }

    @DataProvider
    public Object[][] emptyFieldsCombination () {
        return new Object[][]{
                {"", ""},
                {"", "VCM-TuY-EVZ-r4p"},
                {"Liubomyrned21@gmail.com", ""},
        };
    }
    @Test(dataProvider = "emptyFieldsCombination")
    public void validateEmptyUserEmailAndUserPassword (String userEmail, String userPass){
        linkedinLoginPage.loginReturnLoginPage(userEmail, userPass);
        Assert.assertTrue(linkedinLoginPage.isLoaded(), "Login page is not loaded.");
    }


    @DataProvider
    public Object[][] invalidDataFieldsCombination() {
        return new Object[][]{
                {"a", "a", "Слишком короткий текст (минимальная длина – 3 симв., введено – 1 симв.)", "Пароль должен содержать не менее 6 символов."},
        };
    }

    @Test(dataProvider = "invalidDataFieldsCombination")
    public void validateShortUserEmailAndPassword (String userEmail, String userPass, String userEmailValidationText, String userPasswordValidationText) throws InterruptedException {
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = linkedinLoginPage.loginReturnLoginSubmitPage(userEmail, userPass);
        Thread.sleep(3000);
        Assert.assertTrue(linkedinLoginSubmitPage.isLoaded(), "User is not on LoginSubmit page");
        Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.",
                "Alert box has incorrect message");
        Assert.assertEquals(linkedinLoginSubmitPage.getUserEmailValidationText(), "There were one or more errors in your submission. Please correct the marked fields below.",
                "userEmail field has wrong validation message text");
        Assert.assertEquals(linkedinLoginSubmitPage.getUserPasswordValidationText(), userPasswordValidationText, "userEmail field has wrong validation message text");

    }

}

    /*Home Task #8:
        Add test for Reset Password
        - explore Reset Password scenario manually before automation
        - create test scenario with all necessary page objects
        - Put sleep for few minutes in place where test should get password recovery link from email (While test is sleeping you'll need to get a link from email manually and navigate to that link in Browser that was opened by test so that after sleep Test could proceed with next steps)
        - You test scenario should end up logged in with new password on Home page

        Note: Use PageObject and PageFactory patterns. Avoid using any bad practices.*/