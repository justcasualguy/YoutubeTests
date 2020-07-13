package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testBase.TestBase;

import java.time.Duration;

public class HomePage {
    WebDriver webDriver;
    WebElement loginButton;
    WebElement searchBar;

    public HomePage(WebDriver webDriver){
        this.webDriver = webDriver;

    }

    public void find(String videoName){

        TestBase.fluentWaitForElement(webDriver,
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath(TestBase.getXPath("searchBar")
                ))
        );

        searchBar = webDriver.findElement(
                By.xpath(TestBase.getXPath("searchBar"))
        );
        searchBar.sendKeys(videoName);
        searchBar.sendKeys(Keys.ENTER);
    }

    public void goToHomePage(){
        webDriver.navigate().to(TestBase.getInstance().getEndpoints().getProperty("homePage"));

    }

    public void clickLoginButton(){
        loginButton = webDriver.findElement(
                By.xpath(TestBase.getXPath("loginButton"))
        );
        loginButton.click();

    }



}
