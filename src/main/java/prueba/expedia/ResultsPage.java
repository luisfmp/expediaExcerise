/* (c) Disney. All rights reserved. */
package prueba.expedia;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author lu.martinez
 */
public class ResultsPage {
    private WebDriver driver;

    @FindBy(css = "li[id^=\"flight-module\"]")
    public List<WebElement> results;

    @FindBy(id = "sortDropdown")
    public WebElement sortBy;

    private WebDriverWait wait;

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 20);
        PageFactory.initElements(driver, this);
    }

    public ArrayList<Integer> getPrices() {
        ArrayList<Integer> prices = new ArrayList<Integer>();
        for (WebElement result : results) {
            String stringPrice = result
                    .findElement(By.cssSelector("span[data-test-id=\"listing-price-dollars\"]"))
                    .getText()
                    .substring(1)
                    .replace(",", "");
            prices.add(Integer.parseInt(stringPrice));
        }
        return prices;
    }

    public void selectOrder(String orderBy) {
        Select selector = new Select(sortBy);
        selector.selectByVisibleText(orderBy);
    }

    public void waitForRefreshList() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".segmented-list.results-list")));
        wait
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.cssSelector(".segmented-list.results-list.price-sort")));
    }

    public void waitForLoadBarNotVisible() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.progress-bar")));

    }

}
