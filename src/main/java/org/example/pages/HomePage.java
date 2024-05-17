package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    // Locators
    private By dropdownLocator = By.className("dropdown");
    private By countryLocator(String country) {
        return By.xpath("//span[contains(text(), '" + country + "')]");
    }
    private By cityLocator(String city) {
        return By.xpath("//p[@class='cityElement' and contains(text(), '" + city + "')]");
    }
    private By viewMapButtonLocator = By.id("gotoCityButton");
    private By mapLocator = By.id("mapid");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickDropdown() {
        driver.findElement(dropdownLocator).click();
    }

    public void moveToCountry(String country) {
        WebElement selectCountry = driver.findElement(countryLocator(country));
        Actions actions = new Actions(driver);
        actions.moveToElement(selectCountry).perform();
    }

    public void clickCity(String city) {
        driver.findElement(cityLocator(city)).click();
    }

    public void clickViewMapButton() {
        WebElement viewMapButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(viewMapButtonLocator));
        viewMapButton.click();
    }

    public boolean isMapDisplayed() {
        return driver.findElement(mapLocator).isDisplayed();
    }
}

