import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.TestBase;

public class AddVideoTest {

    WebDriver webDriver;
    HomePage homePage;
    LoginPage loginPage;
    FoundVideosPage foundVideosPage;
    LoggedUserPage loggedUserPage;
    AddVideoPage addVideoPage;

    @BeforeClass
    public void setup() {
        TestBase.getInstance().startNewDriver();
        webDriver = TestBase.getInstance().getWebDriver();
        webDriver.get(
                TestBase.getInstance().getEndpoints().getProperty("homePage")
        );
        homePage = new HomePage(webDriver);
        loginPage = new LoginPage(webDriver);
        loggedUserPage = new LoggedUserPage(webDriver);
        addVideoPage = new AddVideoPage(webDriver);

        webDriver.manage().window().maximize();


    }


    @Test
    public void addVideoTest() {
        homePage.goToHomePage();

        homePage.clickLoginButton();
        loginPage.login(
                TestBase.getInstance().getDefaultProperties().getProperty("email"),
                TestBase.getInstance().getDefaultProperties().getProperty("password")
        );

        loggedUserPage.clickAddVideoButton();
        loggedUserPage.clickUploadVideoButton();
        addVideoPage.clickSelectFileButton();
        addVideoPage.selectFile(TestBase.getInstance().getDefaultProperties().getProperty("uploadedFilePath"));
        addVideoPage.clickNextButton();
        addVideoPage.clickMadeForKidsButton();
        addVideoPage.clickNextButton();
        addVideoPage.clickNextButton();
        addVideoPage.clickPublicVisibilityButton();
        addVideoPage.clickDoneButton();
        addVideoPage.clickCloseButton();


        Assert.assertEquals(
                webDriver.findElement(By.xpath(TestBase.putStringToXPath(
                        TestBase.getXPath("videoCell"),
                        addVideoPage.getRecentlyAddedVideoName())

                )).isDisplayed(), true);
    }

    @AfterClass
    public void cleanUp() {
        addVideoPage.deleteVideo(addVideoPage.getRecentlyAddedVideoName());
        webDriver.close();


    }
}
