package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testBase.TestBase;

import javax.swing.text.Element;

public class ManageSubscriptionsPage {
    WebDriver webDriver;
    WebElement unsubButton;
    WebElement confirmUnsubButton;

    public ManageSubscriptionsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void unsubChannel(String channelName){

        String subButonXPath = TestBase.putStringToXPath(TestBase.getXPath("subscribeButtonOnManagePage"),
                channelName);


        TestBase.fluentWaitForElement(webDriver,
                ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.xpath(subButonXPath),1
                )
        );

        unsubButton = TestBase.findFirstDisplayed(
                webDriver.findElements(
                        By.xpath(subButonXPath)
                )
        );

        unsubButton.click();

        waitForUnsubConfirmButtonToShow();

        clickConfirmUnsubButton();

        TestBase.fluentWaitForElement(webDriver,
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(TestBase.getXPath("confirmSubscriptionPopUp"))
                )
        );

    }

  public void waitForSubbedChannelToShow(String channelName){
      TestBase.fluentWaitForElement(
              webDriver,
              ExpectedConditions.numberOfElementsToBeMoreThan(
                      By.xpath(TestBase.putStringToXPath(
                              TestBase.getXPath("subscribeButtonOnManagePage"),
                              channelName
                              )
                      ),
                      1
              )
      );
  }
  public void waitForUnsubConfirmButtonToShow(){
      TestBase.fluentWaitForElement(webDriver,ExpectedConditions.elementToBeClickable(
              By.xpath(TestBase.getXPath("confirmUnsubButton")
              )));
  }

    public void clickConfirmUnsubButton(){
        confirmUnsubButton = webDriver.findElement(
                By.xpath(TestBase.getXPath("confirmUnsubButton"))
        );
        confirmUnsubButton.click();
    }


}
