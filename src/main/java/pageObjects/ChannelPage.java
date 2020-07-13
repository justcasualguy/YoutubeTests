package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testBase.TestBase;

import java.time.Duration;

public class ChannelPage {
    WebDriver webDriver;
    WebDriverWait wait;
    WebElement subscribeButton;
    String subscribedChannelUrl;
    String displayedChannelName;


    public ChannelPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }



    public void clickSubscribeButton(){
        TestBase.fluentWaitForElement(
                webDriver,
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath(TestBase.getXPath("subscribeButton"))
        ));
        displayedChannelName = webDriver.findElement(
                By.xpath(TestBase.getXPath("channelName"))
        ).getText();
        System.out.println(displayedChannelName + "in ChannelPage clickSubBut");
        subscribeButton = webDriver.findElement(
                By.xpath(TestBase.getXPath("subscribeButton"))
        );
        subscribeButton.click();
        TestBase.fluentWaitForElement(webDriver,
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(TestBase.getXPath("confirmSubscriptionPopUp"))
                )
        );

    }
    public void setDisplayedChannelName(String displayedChannelName) {
        this.displayedChannelName = displayedChannelName;
    }

    public String getDisplayedChannelName() {
        return displayedChannelName;
    }
}
