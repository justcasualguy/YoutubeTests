package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testBase.TestBase;

import java.time.Duration;


public class LoginPage {
    WebDriver webDriver;
    WebElement emailTextField;
    WebElement passwordTextField;
    WebElement loginWindow;

    public LoginPage(WebDriver driver){
        this.webDriver = driver;
    }



    public void enterUsername(String username){
        loginWindow = webDriver.findElement(
                By.xpath(TestBase.getXPath("loginWindow")
        ));
        emailTextField = webDriver.findElement(
                By.xpath(TestBase.getXPath("emailTextField")));

        emailTextField.sendKeys(username);
        emailTextField.sendKeys(Keys.ENTER);


    }

    public void enterPassword(String password){

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TestBase.getXPath("passwordTextField"))));
        passwordTextField = webDriver.findElement(
                By.xpath(TestBase.getXPath("passwordTextField"))
        );

        passwordTextField.sendKeys(password);
        passwordTextField.sendKeys(Keys.ENTER);
    }

    public void login(String username,String password){
        enterUsername(username);
        enterPassword(password);
    }
}
