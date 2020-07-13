package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testBase.TestBase;

import java.time.Duration;
import java.util.List;

public class FoundVideosPage {
    WebDriver webDriver;

    List<WebElement> foudVideos;
    List<WebElement> foudChannels;
    WebDriverWait wait;
    public FoundVideosPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }


    public void clickFirstVideoFound(){

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.xpath(TestBase.getXPath("foundVideos")),0)
        );

        foudVideos = webDriver.findElements(
                By.xpath(TestBase.getXPath("foundVideos"))
        );
        foudVideos.get(0).click();
    }

    public void clickFirstChannelFound(){
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.xpath(TestBase.getXPath("foundVideos")),0)
        );
        foudChannels =  webDriver.findElements(
                By.xpath(TestBase.getXPath("foundChannels"))
        );
        foudChannels.get(0).click();
    }
}
