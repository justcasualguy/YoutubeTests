package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testBase.TestBase;

public class SubscriptionsPage {
    WebDriver webDriver;
    WebElement manageSubscriptionsButton;


    String subscribedChannelName;

    public SubscriptionsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickManageSubscriptionsButton() {
        TestBase.fluentWaitForElement(
                webDriver,
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath(TestBase.getXPath("manageSubscriptionsButton")
                        )
                )
        );


        manageSubscriptionsButton = webDriver.findElement(
                By.xpath(TestBase.getXPath("manageSubscriptionsButton"))
        );
        manageSubscriptionsButton.click();


    }



    public String getSubscribedChannelName() {
        return subscribedChannelName;
    }

    public void setSubscribedChannelName(String subscribedChannelName) {
        this.subscribedChannelName = subscribedChannelName;
    }


}


