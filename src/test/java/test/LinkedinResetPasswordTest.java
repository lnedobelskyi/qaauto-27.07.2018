package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LinkedinLoginPage;
import page.LinkedinPasswordResetSubmitPage;
import page.LinkedinRequestPasswordResetPage;
import page.LinkedinSetNewPasswordPage;

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

    @Test
    public void successfulResetPasswordTest() throws InterruptedException {
        Assert.assertTrue(linkedinLoginPage.isLoaded(), "LoginPage is not loaded.");

        LinkedinRequestPasswordResetPage linkedinRequestPasswordResetPage = linkedinLoginPage.clickOnForgotPasswordLink();
        Assert.assertTrue(linkedinRequestPasswordResetPage.isLoaded(), "RequestPasswordResetPage is not loaded.");

        LinkedinPasswordResetSubmitPage linkedinPasswordResetSubmitPage = linkedinRequestPasswordResetPage.findAccount("liubomyrned21@gmail.com");
        Assert.assertTrue(linkedinPasswordResetSubmitPage.isLoaded(), "PasswordResetSubmitPage is not loaded.");

        LinkedinSetNewPasswordPage linkedinSetNewPasswordPage = linkedinPasswordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(linkedinSetNewPasswordPage.isLoaded(), "SetNewPasswordPage is not loaded.");
    }
}