import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinHomePage {

        private WebDriver browser;
        WebElement profileNavigationItem;

    public LinkedinHomePage(WebDriver browser){
        this.browser = browser;
        initElements();
    }
    private void initElements(){
        profileNavigationItem = browser.findElement(By.xpath("//*[@id='profile-nav-item']"));

    }

    public String getCurrentPageTitle(){
        return browser.getTitle();

    }
    public String getCurrentPageURL(){
        return browser.getCurrentUrl();

    }

    public boolean isLoaded() {
        return profileNavigationItem.isDisplayed() && getCurrentPageTitle().contains("(1) LinkedIn") && getCurrentPageURL().contains("/feed/");
    }
}