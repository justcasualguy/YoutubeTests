package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.TestBase;

import java.time.Duration;


public class LoggedUserPage {
    WebDriver webDriver;
    WebDriverWait wait;
    WebElement guideButton;
    WebElement likedVideosButton;
    WebElement subscriptionsButton;
    WebElement addVideoButton;
    WebElement uploadVideoButton;

    public LoggedUserPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    public void clickGuideButton(){
        guideButton=webDriver.findElement(
                By.xpath(TestBase.getXPath("guideButton"))
        );
        guideButton.click();
    }

    public void clickLikedVideos(){


        likedVideosButton = webDriver.findElement(
          By.xpath(TestBase.getXPath("likedVideosButton"))
        );
        wait.until(ExpectedConditions.elementToBeClickable(likedVideosButton));

        likedVideosButton.click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ytd-playlist-video-renderer")));
    }

    public void clickSubscriptionsButton(){
        TestBase.fluentWaitForElement(
                webDriver,
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath(TestBase.getXPath("confirmSubscriptionPopUp"))
                )
        );

         subscriptionsButton = webDriver.findElement(
          By.xpath(TestBase.getXPath("subscriptionsButton"))
        );
        subscriptionsButton.click();
    }
    public void clickAddVideoButton(){
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(TestBase.getXPath("addVideoButton")
        )));
        addVideoButton = webDriver.findElement(
                By.xpath(TestBase.getXPath("addVideoButton"))
        );
        addVideoButton.click();
    }

    public void clickUploadVideoButton(){
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(TestBase.getXPath("uploadVideoButton"))
                ));
        uploadVideoButton=webDriver.findElement(
                By.xpath(TestBase.getXPath("uploadVideoButton"))
        );

        uploadVideoButton.click();
    }

}
