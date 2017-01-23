package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertEquals;

/**
 * Created by vitalii on 1/22/17.
 */
public class PaymentPage {

    private static final By mainApplicantTitleDropdown = By.xpath("//div[@class='info-text']/following::div[1]//select");
    private static final By mainApplicantFirstName = By.xpath("//div[@class='info-text']/following::div[1]//input[@ng-model='passenger.name.first']");
    private static final By mainApplicantLastName = By.xpath("//div[@class='info-text']/following::div[1]//input[@ng-model='passenger.name.last']");
    private static final By secondApplicantTitleDropdown = By.xpath("//div[@class = 'horizontal-line']/following::select");
    private static final By secondApplicantFirstName = By.xpath("//div[@class = 'horizontal-line']/following::input[@ng-model='passenger.name.first']");
    private static final By secondApplicantLastName = By.xpath("//div[@class = 'horizontal-line']/following::input[@ng-model='passenger.name.last']");
    private static final By childApplicantFirstName = By.xpath("//hr[@ng-if='::!paxForm.config.guestsForm']/preceding::div[@class='row'][1]//input[@ng-model='passenger.name.first']");
    private static final By childApplicantLastName = By.xpath("//hr[@ng-if='::!paxForm.config.guestsForm']/preceding::div[@class='row'][1]//input[@ng-model='passenger.name.last']");
    private static final By countryDropdown = By.xpath("//div[@class='phone-number-country']//select");
    private static final By phoneNumberField = By.xpath("//div[@class='phone-number']//input");
    private static final By cardNumberInputField = By.xpath("//label[contains(text(), 'Card number')]/..//input");
    private static final By cardTypeDropdown = By.xpath("//label[contains(text(), 'Card type')]/..//select");
    private static final By cardExpiryMonthDropdown = By.xpath("//select[@ng-change='cm.expiryMonthUpdate()']");
    private static final By cardExpiryYearDropdown = By.xpath("//select[@ng-change='cm.expiryYearUpdate()']");
    private static final By cardCVVField = By.xpath("//div[@class='card-security-code']/input");
    private static final By cardHolderNameField = By.name("cardHolderName");
    private static final By address1Field = By.xpath("//label[contains(text(), 'Address 1')]/..//input");
    private static final By address2Field = By.xpath("//label[contains(text(), 'Address 2')]/..//input");
    private static final By cityField = By.xpath("//label[contains(text(), 'City')]/..//input");
    private static final By postalCodeField = By.xpath("//label[contains(text(), 'Postcode/ZIP code')]/..//input");
    private static final By acceptAgreementCheckbox = By.name("acceptPolicy");
    private static final By payButton = By.xpath("//div[@class='terms']/following::button[@class='core-btn-primary core-btn-medium']");
    private static final By paymentErrorPromtMessage = By.xpath("//prompt[@class='error']");
    public static String expectedErrorMessage = "Oh. There was a problem\n"+
            "As your payment was not authorised we could not complete your reservation. Please ensure that the information was correct or use a new payment to try again";

    private WebDriver driver;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    public PaymentPage userInsertsPassengersData() throws InterruptedException {
        insertFirstApplicantData();
        insertSecondApplicantData();
        insertChildApplicantData();
        insertPaymentData();
        clickPayButton();
        return new PaymentPage(driver);
    }

    public PaymentPage userPaysForTickets() throws InterruptedException {
        insertPaymentData();
        clickPayButton();
        Thread.sleep(6000);
        return new PaymentPage(driver);
    }

    public void insertFirstApplicantData () {
        driver.findElement(mainApplicantTitleDropdown).sendKeys("Mr");
        driver.findElement(mainApplicantFirstName).sendKeys("FirstPassengerFirst");
        driver.findElement(mainApplicantLastName).sendKeys("FirstPassangerLast");
    }

    public void insertSecondApplicantData () {
        driver.findElement(secondApplicantTitleDropdown).sendKeys("Mrs");
        driver.findElement(secondApplicantFirstName).sendKeys("SecondPassengerFirst");
        driver.findElement(secondApplicantLastName).sendKeys("SecondPassangerLast");
    }

    public void insertChildApplicantData () {
        driver.findElement(childApplicantFirstName).sendKeys("ChildPassengerFirst");
        driver.findElement(childApplicantLastName).sendKeys("ChildPassangerLast");
    }

    public void insertPaymentData () {
        driver.findElement(countryDropdown).sendKeys("Poland");
        driver.findElement(phoneNumberField).sendKeys("732863370");
        driver.findElement(cardNumberInputField).sendKeys("5555555555555557");
        driver.findElement(cardTypeDropdown).sendKeys("MasterCard");
        driver.findElement(cardExpiryMonthDropdown).sendKeys("10");
        driver.findElement(cardExpiryYearDropdown).sendKeys("2018");
        driver.findElement(cardCVVField).sendKeys("265");
        driver.findElement(cardHolderNameField).sendKeys("John Smith");
        driver.findElement(address1Field).sendKeys("Address 1");
        driver.findElement(address2Field).sendKeys("Address 2");
        driver.findElement(cityField).sendKeys("City");
        driver.findElement(postalCodeField).sendKeys("12345");
        if ( !driver.findElement(acceptAgreementCheckbox).isSelected() )
        {
            driver.findElement(acceptAgreementCheckbox).click();
        }
    }

    private void clickPayButton () throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(payButton).click();
        Thread.sleep(3000);
    }

    public PaymentPage userVerifiesPaymentErrorMessage () throws InterruptedException {
        clickPayButton();
        Thread.sleep(6000);
        WebElement element = driver.findElement(paymentErrorPromtMessage);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);

        String actual = driver.findElement(paymentErrorPromtMessage).getText();
        assertEquals(actual, expectedErrorMessage);
        return new PaymentPage(driver);

    }
}
