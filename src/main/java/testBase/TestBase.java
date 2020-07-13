package testBase;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class TestBase {
    private static TestBase testBase;
    private WebDriver webDriver;
    private Properties defaultProperties;
    private static Properties xPaths;
    private Properties endpoints;


    private TestBase() {

        defaultProperties = new Properties();
        xPaths = new Properties();
        endpoints = new Properties();
        setupProperties();
        System.setProperty("webdriver.chrome.driver",
                defaultProperties.getProperty("chromeDriverPath"));



    }

    public static TestBase getInstance() {
        if (testBase == null)
            testBase = new TestBase();

        return testBase;
    }

    public void setupProperties() {
        InputStream dps = getClass().getResourceAsStream("/default.properties");
        InputStream xps = getClass().getResourceAsStream("/xpaths.properties");
        InputStream eps = getClass().getResourceAsStream("/endpoints.properties");
        try {
            defaultProperties.load(dps);
        } catch (IOException e) {
            System.out.println("default.properties file is not found in resources folder");
        }
        try {
            xPaths.load(xps);
        } catch (IOException e) {
            System.out.println("xpath.properties file is not found in resources folder");
        }
        try {
            endpoints.load(eps);
        } catch (IOException e) {
            System.out.println("endpoints.properties file is not found in resources folder");
        }

    }

    public static void fluentWaitForElement(WebDriver webDriver, ExpectedCondition expectedCondition) {
        FluentWait wait = new FluentWait<>(webDriver).
                withTimeout(Duration.ofSeconds(10)).
                pollingEvery(Duration.ofSeconds(1)).
                ignoring(NoSuchElementException.class);
        try {
            wait.until(expectedCondition);
        } catch (TimeoutException e) {
            Assert.fail("Element not found");
        }

    }

    public static WebElement findFirstDisplayed(List<WebElement> webElements) {
        for (WebElement element : webElements)
            if (element.isDisplayed())
                return element;
        return null;
    }

    public static String putStringToXPath(String xpath,String put){
        return String.format(xpath,put);
    }
    public void startNewDriver(){
        webDriver = new ChromeDriver();
    }
    public static String getXPath(String propertyName){
        return xPaths.getProperty(propertyName);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public Properties getDefaultProperties() {
        return defaultProperties;
    }

    public Properties getxPaths() {
        return xPaths;
    }

    public Properties getEndpoints() {
        return endpoints;
    }

}
