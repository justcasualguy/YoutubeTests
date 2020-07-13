package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testBase.TestBase;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class AddVideoPage {
    WebDriver webDriver;
    WebElement selectFileButton;
    WebElement nextButton;
    WebElement madeForKidsButton;
    WebElement publicVisibilityButton;
    WebElement closeButton;
    WebElement doneButton;
    WebElement videoCell;
    WebElement videoOptionsButton;
    WebElement deleteForeverButton;
    WebElement deleteVideoCheckbox;
    WebElement deleteConfirmButton;
    WebDriverWait wait;
    String recentlyAddedVideoName;

    public AddVideoPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    public void deleteVideo(String videoName){
        hoverVideoCell(videoName);
        clickVideoOptionsButton();
        clickDeleteForeverButton();
        clickDeleteVideoCheckbox();
        clickDeleteConfirmButton();
    }
    public void clickVideoOptionsButton(){
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(TestBase.getXPath("videoOptionsButton"))
        ));
        videoOptionsButton = webDriver.findElement(
                By.xpath(TestBase.getXPath("videoOptionsButton"))
        );
        videoOptionsButton.click();
    }
    public void clickDeleteForeverButton(){
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(TestBase.getXPath("deleteForeverButton"))
        ));
        deleteForeverButton = webDriver.findElement(
                By.xpath(TestBase.getXPath("deleteForeverButton"))
        );
        deleteForeverButton.click();
    }

    public void clickDeleteConfirmButton(){
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(TestBase.getXPath("deleteConfirmButton"))
        ));

        deleteConfirmButton = webDriver.findElement(
                By.xpath(TestBase.getXPath("deleteConfirmButton"))
        );
        deleteConfirmButton.click();
    }
    public void clickDeleteVideoCheckbox(){
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(TestBase.getXPath("deleteVideoCheckbox"))
        ));
        deleteVideoCheckbox = webDriver.findElement(
                By.xpath(TestBase.getXPath("deleteVideoCheckbox"))
        );
        deleteVideoCheckbox.click();
    }

    public void clickSelectFileButton(){
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(TestBase.getXPath("selectFileButton"))
        ));

        selectFileButton = webDriver.findElement(
                By.xpath(TestBase.getXPath("selectFileButton"))
        );
        selectFileButton.click();

    }

    public  void clickNextButton(){
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(TestBase.getXPath("nextButton"))
                )
        );
        nextButton = webDriver.findElement(
                By.xpath(TestBase.getXPath("nextButton"))
        );
        nextButton.click();

    }
    public void clickMadeForKidsButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(TestBase.getXPath("madeForKidsButton"))
        ));
        madeForKidsButton = webDriver.findElement(
                By.xpath(TestBase.getXPath("madeForKidsButton"))
        );
        madeForKidsButton.click();
    }

    public void clickPublicVisibilityButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(TestBase.getXPath("publicVisibilityButton"))
        ));
        publicVisibilityButton = webDriver.findElement(
                By.xpath(TestBase.getXPath("publicVisibilityButton"))
        );
        publicVisibilityButton.click();
    }

    public void clickDoneButton(){
        waitForFileUpload();
        doneButton=webDriver.findElement(
                By.xpath(TestBase.getXPath("doneButton"))
        );
        doneButton.click();
    }
    public void clickCloseButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(TestBase.getXPath("closeButton"))
        ));
        closeButton = webDriver.findElement(
                By.xpath(TestBase.getXPath("closeButton"))
        );
        closeButton.click();
        TestBase.fluentWaitForElement(webDriver,
                ExpectedConditions.elementToBeClickable(
                By.xpath(TestBase.getXPath("videoListRow"))
        ));
    }
    public void hoverVideoCell(String videoName){
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(TestBase.putStringToXPath(
                        TestBase.getXPath("videoCell"),
                        videoName
                ))
        ));
        videoCell=webDriver.findElement(
                By.xpath(TestBase.putStringToXPath(
                        TestBase.getXPath("videoCell"),
                        videoName
                ))
        );
        Actions action = new Actions(webDriver);
        action.moveToElement(videoCell).perform();
    }


    public void selectFile(String path){
        Robot robot = null;
        String[] splitedPath;
        StringSelection stringSelection = new StringSelection(path);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        splitedPath = path.split("\\\\");

        recentlyAddedVideoName = splitedPath[splitedPath.length-1].split("\\.")[0];
        System.out.println(recentlyAddedVideoName);
        try {
            Thread.sleep(1000);
            robot = new Robot();
            clipboard.setContents(stringSelection, stringSelection);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }


    }


    public void waitForFileUpload(){
        WebDriverWait longerWait = new WebDriverWait(webDriver,Duration.ofMinutes(10));
        longerWait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath(TestBase.getXPath("videoProcessed"))
                )
        );
    }

    public String getRecentlyAddedVideoName() {
        return recentlyAddedVideoName;
    }
}
