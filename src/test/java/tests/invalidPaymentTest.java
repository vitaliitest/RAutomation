package tests;

import com.sun.jna.platform.win32.Sspi;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.BookingPage;
import pages.HomePage;
import pages.PaymentPage;

import java.util.concurrent.TimeUnit;


/**
 * Created by vitalii on 1/21/17.
 */
public class invalidPaymentTest {

    private WebDriver driver;

    @BeforeClass
    private void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/vitalii/Documents/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.ryanair.com/ie/en/");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 3);

        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1250, 800));
    }

    @Test
    public void test() throws InterruptedException {
        HomePage loginPage = new HomePage(driver);
        loginPage.userLogsIn();
        Thread.sleep(2000);
        loginPage.userInsertFlightDetails();
        BookingPage bookingPage = new BookingPage(driver);
        bookingPage.userSelectsTickets();
        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.userInsertsPassengersData();
        paymentPage.userPaysForTickets();
        paymentPage.userVerifiesPaymentErrorMessage();
    }

    @AfterClass
    private void tearDown() {
        driver.quit();
    }
}
