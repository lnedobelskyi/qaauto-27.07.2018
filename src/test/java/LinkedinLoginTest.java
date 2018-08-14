import org.mockito.internal.matchers.Null;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        System.setProperty("webdriver.gecko.driver", "/Users/liubomyrned/Downloads/geckodriver");
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
                { "liubomyrned21@gmail.com", "VCM-TuY-EVZ-r4p"},
                { "liubomyrNED21@gmail.com", "VCM-TuY-EVZ-r4p"},
        };
    }

    @Test(dataProvider="validFieldsCombination")
    public void successfullLoginTest(String userEmail, String userPass) {
        linkedinLoginPage.login(userEmail,userPass );
        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(browser);
        Assert.assertTrue(linkedinHomePage.isLoaded(), "Home page is loaded.");
    }

    @Test
    public void negativeLoginTest() {
        linkedinLoginPage.login("tph", "tph2");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);
        Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(),
                "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.",
                "Alert box has incorrect message");
    }

    @Test
    public void negativeLiginTestSignInButtonIsDisabled() {
        linkedinLoginPage.login("", "");
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(browser);
        Assert.assertFalse(linkedinLoginPage.SignInButtonIsDisabled(), "Button is Enabled");
    }

    @Test
    public void negativeLiginTestLoginFieldIsEmpty() {
        linkedinLoginPage.login("", "hhrr");
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(browser);
        Assert.assertFalse(linkedinLoginPage.SignInButtonIsDisabled(), "Button is Enabled");
    }

    @Test
    public void negativeLiginTestPassFieldIsEmpty() {
        linkedinLoginPage.login("liubomyrned21@gmail.com", "");
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(browser);
        Assert.assertFalse(linkedinLoginPage.SignInButtonIsDisabled(), "Button is Enabled");
    }

    @Test
    public void negativeLiginTestPassIsWithLowercase() {
        linkedinLoginPage.login("liubomyrned21@gmail.com", "vCM-TuY-EVZ-r4p");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);
        Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(),
                "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.",
                "Alert box has incorrect message");
    }

    @DataProvider
    public Object[][] emptyFieldsCombination() {
        return new Object[][]{
                { "", ""},
                { "", "P@ssword123"},
                { "someone@domain.com", ""},
        };
    }

    @Test(dataProvider="emptyFieldsCombination")
    public void validateEmptyUserEmailAndUserPassword(String userEmail, String userPass){
        linkedinLoginPage.login(userEmail, userPass);
        Assert.assertTrue(linkedinLoginPage.isLoaded(), "User is not on Login page.");
    }

    @Test
    public void validateShortUserEmailAndPassword(){
        linkedinLoginPage.login("a", "a");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);
        Assert.assertTrue(linkedinLoginSubmitPage.isLoaded(), "User is not on Login Submit page.");

        Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(),
                "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.",
                "Alert box has incorrect message");
        Assert.assertEquals(linkedinLoginSubmitPage.getUserEmailValidationText(),
                "The text you provided is too short (the minimum length is 3 characters, your text contains 1)",
                "userEmail field has wrong validation message text.");
        Assert.assertEquals(linkedinLoginSubmitPage.getPasswordValidationText(),
                "The password you provided must have at least 6 characters.",
                "userEmail field has wrong validation message text");

    }
}



//Home Task #5:
//Add all 'required' negavite tests to LinkedinLoginTest
//- Do manual exploratory testing of negative login scenario.
//- Based on your manual exploratory testing add(implement) all required tests that you revealed.
//Note: be ready to explain which tests you automated and why.
