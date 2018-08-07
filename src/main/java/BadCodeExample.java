import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;

import java.util.List;

public class BadCodeExample {
    public static void main(String args[]) throws InterruptedException {
        System.out.println("Hello world!!!");
        System.setProperty("webdriver.gecko.driver","/Users/liubomyrned/Downloads/geckodriver");
        WebDriver browser = new FirefoxDriver();
        browser.get("http://www.google.com");
        WebElement queryField = browser.findElement(By.name("q"));
        queryField.sendKeys("selenium");
        queryField.sendKeys (Keys.ENTER);
        Thread.sleep(3000);

        // Verify that results list contains 10 elements
        List<WebElement> searchResults = browser.findElements(By.cssSelector("div.srg div.g"));
        System.out.println("Results count: "+searchResults.size());
        if (searchResults.size()<5) {
            System.out.println("Results count "+ searchResults.size()+" is incorrect");
        }else{
            System.out.println("Results count "+ searchResults.size()+" is correct");

        // Verify that each result item contains searchterm
        for (WebElement searchResult: searchResults) {
            String searchResultText = searchResult.getText();
            if (searchResultText.contains("Selenium")) {
                System.out.println("Searchterm found");
            } else {
                System.out.println("Searchterm is not found");
            }
        }
        }
    }
}