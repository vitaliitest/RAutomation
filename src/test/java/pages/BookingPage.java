package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by vitalii on 1/22/17.
 */
public class BookingPage {
    private static final By continueToSearchButton = By.xpath("//div[@class='biz-popup-benefits']/button");
    private static final By firstAvailableFlightDiv = By.xpath("//div[@class='flights-table-price']");
    private static final By continueLeisureButton = By.xpath("//span[contains(text(), 'Leisure Plus')]/parent::div/parent::div//button");
    private static final By continueButton = By.id("continue");
    private static final By firstPersonSeatButton = By.xpath("//div[@class = 'ranimate-seat-rows'][5]//span[@ng-click='$ctrl.addSeat(seat)']");
    private static final By secondPersonSeatButton = By.xpath("//div[@class = 'ranimate-seat-rows'][6]//span[@ng-click='$ctrl.addSeat(seat)']");
    private static final By thirdPersonSeatButton = By.xpath("//div[@class = 'ranimate-seat-rows'][7]//span[@ng-click='$ctrl.addSeat(seat)']");
    private static final By nextButton = By.xpath("//button[@class = 'core-btn-primary next']");
    private static final By checkOutButton = By.xpath("//span[contains(text(), 'Check out')]/..");

    private WebDriver driver;

    public BookingPage(WebDriver driver) {
        this.driver = driver;
    }

    public PaymentPage userSelectsTickets() throws InterruptedException {
        closeBusinessPlusDialogWindow();
        selectFirstAvailableFlight();
        Thread.sleep(3000);
        selectLeisurePlusPrice();
        Thread.sleep(3000);
        clickContinueButton();
        Thread.sleep(4000);
        selectSeat();
        Thread.sleep(3000);
        clickNextButton();
        Thread.sleep(3000);
        clickNextButton();
        Thread.sleep(4000);
        clickCheckOutButton();
        Thread.sleep(4000);
        return new PaymentPage(driver);

    }

    public void closeBusinessPlusDialogWindow() throws InterruptedException {
        driver.findElement(continueToSearchButton).click();
        Thread.sleep(2000);
    }

    public void selectFirstAvailableFlight() {
        driver.findElement(firstAvailableFlightDiv).click();
    }

    public void selectLeisurePlusPrice () {
        driver.findElement(continueLeisureButton).click();
    }

    public void clickContinueButton()  {
        driver.findElement(continueButton).click();
    }

    public void selectSeat () throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,750)", "");
        Thread.sleep(3000);
        driver.findElement(firstPersonSeatButton).click();
        Thread.sleep(2000);
        driver.findElement(secondPersonSeatButton).click();
        Thread.sleep(2000);
        driver.findElement(thirdPersonSeatButton).click();
    }

    public void clickNextButton() throws InterruptedException {
        driver.findElement(nextButton).click();
        Thread.sleep(3000);
    }

    public void clickCheckOutButton() throws InterruptedException {
        driver.findElement(checkOutButton).click();
        Thread.sleep(3000);
    }
}
