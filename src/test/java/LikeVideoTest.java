import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.TestBase;

import java.time.Duration;

public class LikeVideoTest {
    HomePage homePage;
    LoginPage loginPage;
    WebDriver webDriver;
    LoggedUserPage loggedUserPage;
    FoundVideosPage foundVideosPage;
    VideoPage videoPage;
    LikedVideosPage likedVideosPage;


    @BeforeClass
    public void setup() {
        TestBase.getInstance().startNewDriver();
        webDriver = TestBase.getInstance().getWebDriver();

        homePage = new HomePage(webDriver);
        loginPage = new LoginPage(webDriver);
        foundVideosPage = new FoundVideosPage(webDriver);
        videoPage = new VideoPage(webDriver);
        loggedUserPage = new LoggedUserPage(webDriver);
        likedVideosPage = new LikedVideosPage(webDriver);
        webDriver.manage().window().maximize();


    }


    @Test
    public void likeVideo() {
        homePage.goToHomePage();

        homePage.clickLoginButton();
        loginPage.login(
                TestBase.getInstance().getDefaultProperties().getProperty("email"),
                TestBase.getInstance().getDefaultProperties().getProperty("password")
        );


        homePage.find("video");
        foundVideosPage.clickFirstVideoFound();
        videoPage.clickLikeButton();

        loggedUserPage.clickGuideButton();
        loggedUserPage.clickLikedVideos();


        Assert.assertNotEquals(
                webDriver.findElements(
                        By.xpath(TestBase.putStringToXPath(TestBase.getXPath("likedVideoTitleOnLikedPlaylist"),
                                videoPage.getLikedVideoTitle()
                                )
                        )).size(), 0
        );

    }

    @AfterClass
    public void close() {
        likedVideosPage.clickLikedVideo(videoPage.getLikedVideoTitle());
        videoPage.clickLikeButton();
        webDriver.close();
    }
}
