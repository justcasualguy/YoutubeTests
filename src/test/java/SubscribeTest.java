import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.TestBase;

public class SubscribeTest {

    WebDriver webDriver;
    HomePage homePage;
    LoggedUserPage loggedUserPage;
    FoundVideosPage foundVideosPage;
    LoginPage loginPage;
    ChannelPage channelPage;
    SubscriptionsPage subscriptionsPage;
    ManageSubscriptionsPage manageSubscriptionsPage;


    @BeforeClass
    public void setup() {
        TestBase.getInstance().startNewDriver();
        webDriver = TestBase.getInstance().getWebDriver();
        homePage = new HomePage(webDriver);
        loginPage = new LoginPage(webDriver);
        foundVideosPage = new FoundVideosPage(webDriver);
        loggedUserPage = new LoggedUserPage(webDriver);
        channelPage = new ChannelPage(webDriver);
        subscriptionsPage = new SubscriptionsPage(webDriver);
        manageSubscriptionsPage = new ManageSubscriptionsPage(webDriver);
        webDriver.manage().window().maximize();

    }


    @Test
    public void subscribeTest() {


        homePage.goToHomePage();

        homePage.clickLoginButton();
        loginPage.login(
                TestBase.getInstance().getDefaultProperties().getProperty("email"),
                TestBase.getInstance().getDefaultProperties().getProperty("password"));


        homePage.find("peppa official");

        foundVideosPage.clickFirstChannelFound();

        channelPage.clickSubscribeButton();

        loggedUserPage.clickSubscriptionsButton();

        subscriptionsPage.clickManageSubscriptionsButton();

        Assert.assertNotEquals(
                webDriver.findElements(By.xpath(
                        TestBase.putStringToXPath(
                                TestBase.getXPath("channelNameOnManagePage"),
                                channelPage.getDisplayedChannelName()
                        )
                        )
                )
                , 0
        );

    }


    @AfterClass
    public void cleanUp() {
        manageSubscriptionsPage.unsubChannel(channelPage.getDisplayedChannelName());
        webDriver.close();
    }

}
