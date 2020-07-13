package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testBase.TestBase;

import java.time.Duration;

public class LikedVideosPage {
    WebDriver webDriver;
    WebElement likedVideo;
    String likedVideoUrl;
    String likedVideoName;
    WebDriverWait wait;
    public LikedVideosPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver,Duration.ofSeconds(10));
    }

    public void clickLikedVideo(String likedVideoName){
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(TestBase.putStringToXPath(
                        TestBase.getXPath("likedVideoTitleOnLikedPlaylist"),likedVideoName)
                )
        ));
        likedVideo = webDriver.findElement(
                By.xpath(TestBase.putStringToXPath(
                        TestBase.getXPath("likedVideoTitleOnLikedPlaylist"),likedVideoName)
                )
        );
        likedVideo.click();
    }




    public void setLikedVideoUrl(String likedVideoUrl) {
        this.likedVideoUrl = likedVideoUrl;
    }
    public void setLikeVideoName(String likeVideoName){
        this.likedVideoName=likeVideoName;
    }
}
