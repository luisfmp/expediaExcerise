/* (c) Disney. All rights reserved. */
package prueba.expedia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author lu.martinez
 */
public class ExpediaHomePage {
    private WebDriver driver;

    @FindBy(id = "tab-flight-tab-hp")
    private WebElement flightsButton;

    @FindBy(id = "flight-origin-hp-flight")
    public WebElement originAirport;

    @FindBy(id = "flight-destination-hp-flight")
    public WebElement destinationAirport;

    @FindBy(id = "flight-departing-hp-flight")
    public WebElement departingDate;

    @FindBy(id = "flight-returning-hp-flight")
    public WebElement returningDate;

    @FindBy(css = "form#gcw-flights-form-hp-flight button.btn-primary.gcw-submit")
    public WebElement searchButton;

    public ExpediaHomePage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.expedia.com/");
        PageFactory.initElements(driver, this);
    }

    public void clickAtFlightsButton() {
        flightsButton.click();
    }
}
