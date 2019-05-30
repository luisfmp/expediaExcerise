package prueba.expedia;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class ExpediaOrderTest
        extends TestCase {
    private WebDriver driver;

    @Override
    @Before
    public void setUp() throws Exception {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Override
    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() {
        ExpediaHomePage home = new ExpediaHomePage(driver);
        home.getClass();
        home.clickAtFlightsButton();
        home.returningDate.sendKeys("09/23/2019");
        home.departingDate.sendKeys("09/19/2019");
        home.originAirport.sendKeys("mex");
        home.destinationAirport.sendKeys("cun");
        home.searchButton.click();
        ResultsPage results = new ResultsPage(driver);
        results.waitForLoadBarNotVisible();
        results.selectOrder("Price (Highest)");
        results.waitForRefreshList();
        ArrayList<Integer> prices = results.getPrices();
        for (int i = 1; i < prices.size(); i++) {
            assertTrue(prices.get(i) <= prices.get(i - 1));
        }

    }
}
