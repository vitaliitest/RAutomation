package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by vitalii on 1/21/17.
 */
public class HomePage {

    private static final By loginButton = By.id("myryanair-auth-login");
    private static final By emailTextField = By.name("emailAddress");
    private static final By passwordTextField = By.xpath("//div[@class='form-field password']//input");
    private static final By logInButton = By.xpath("//button[@class='core-btn-primary']");
    private static final By oneWayRadiobutton = By.id("flight-search-type-option-one-way");
    private static final By departureAirportTextField = By.xpath("//input[@placeholder = 'Departure airport']");
    private static final By destinationAirportTextField = By.xpath("//input[@placeholder = 'Destination airport']");
    private static final By dublinAirportButton = By.xpath("//span[contains(text(),'Dublin')]");
    private static final By berlinSfxAirportButton = By.xpath("//span[contains(text(),'Berlin (SXF)')]");
    private static final By flyOutDateButton = By.xpath("//li[@class='calendar-view'][2]//li[27]/span");
    private static final By searchFlightsButton = By.xpath("//button[@ng-click = 'searchFlights()']");
    private static final By passengerNumbeDropdown = By.xpath("//div[@aria-label= '1 Passenger']");
    private static final By addAdultButton = By.xpath("//div[@sub-label = '16+ years']//button[@class='core-btn inc core-btn-wrap']");
    private static final By addChildButton = By.xpath("//div[@sub-label = '2-11 years']//button[@class='core-btn inc core-btn-wrap']");


    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage userLogsIn() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(loginButton).click();
        Thread.sleep(3000);
        driver.findElement(emailTextField).sendKeys("runtest@zoho.com");
        driver.findElement(passwordTextField).sendKeys("Password1");
        driver.findElement(logInButton).click();
        return new HomePage(driver);
    }

    public BookingPage userInsertFlightDetails() throws InterruptedException{
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)", "");
        driver.findElement(oneWayRadiobutton).click();
        selectDepartureAirport("Ireland");
        selectDestinationAirport("Germany");
        selectFlyDate();
        enterPassengerNumber();
        clickSearchFlightButton();
        Thread.sleep(2000);
        return new BookingPage(driver);
    }

    public void selectDepartureAirport (String country) throws InterruptedException {
        driver.findElement(departureAirportTextField).clear();
        driver.findElement(departureAirportTextField).sendKeys(country);
        Thread.sleep(2000);
        driver.findElement(dublinAirportButton).click();
    }

    public void selectDestinationAirport (String country) throws InterruptedException {
        driver.findElement(destinationAirportTextField).clear();
        driver.findElement(destinationAirportTextField).sendKeys(country);
        Thread.sleep(2000);
        driver.findElement(berlinSfxAirportButton).click();
    }

    public void selectFlyDate() {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)", "");
        driver.findElement(flyOutDateButton).click();
    }

    public void clickSearchFlightButton () throws InterruptedException {
        driver.findElement(searchFlightsButton).click();
        Thread.sleep(2000);
    }

    public void enterPassengerNumber () {
        driver.findElement(passengerNumbeDropdown).click();
        driver.findElement(addAdultButton).click();
        driver.findElement(addChildButton).click();
    }

    public void waitForElementIsClickable (WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementIsVisible (WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(locator) );
    }

    public void waitForElementIsNotVisible (WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.invisibilityOfElementLocated((By) locator) );
    }
}
