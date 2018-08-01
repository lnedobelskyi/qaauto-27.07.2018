import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

        // Verify that each result item contains searchterm
        for (WebElement searchResult: searchResults) {
           String searchResultText = searchResult.getText();
           System.out.println(searchResultText);
           if (searchResults.size()<10) {
               System.out.println("Results count "+ searchResults.size()+" is incorrect");
           }else{
               System.out.println("Results count "+ searchResults.size()+" is correct");
           }
        }


        //Home Task #2:
        //Use if/else operators to add simple verifications in Google search scenario
        //- Print message "Results count is correct" if there are 10 results and print "Results count is incorrect" if not 10.
        //- Print message "Searchterm found" if searchterm is present in result item and "Searchterm not found" if not.
        //browser.close();
    }
}
