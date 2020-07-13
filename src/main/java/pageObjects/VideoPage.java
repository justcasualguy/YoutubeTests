package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testBase.TestBase;

import java.time.Duration;

public class VideoPage {

    WebDriver webDriver;
    WebElement likeButton;
    String likedVideoTitle;


    public VideoPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickLikeButton(){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(TestBase.getXPath("likeButton"))));

        likeButton = webDriver.findElement(
                By.xpath(TestBase.getXPath("likeButton"))
        );

        likedVideoTitle = webDriver.findElement(
                By.xpath(TestBase.getXPath("likedVideoTitle"))
        ).getText();

        likeButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(TestBase.getXPath("confirmPopUp"))
        ));
    }

    public String getLikedVideoTitle() {
        return likedVideoTitle;
    }
}
