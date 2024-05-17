package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BitolaPage {

    private WebDriver driver;

    private By detailsButtonLocator = By.id("btn-details");

    private By languageDropdownButtonLocator = By.id("languageDropdownButton");

    private By languageDropdownMenuLocator = By.className("dropdown-menu");

    private By scrollPanelLocator = By.className("panel-default");
    private By dateLocator = By.className("current-date");
    private By timeLocator = By.className("current-time");

    public BitolaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickDetailsButton() {
        WebElement panelContainer = driver.findElement(scrollPanelLocator);

        // Scroll to a specific position inside the panel
        int scrollTopValue = 500;
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[1];", panelContainer, scrollTopValue);

        // Wait for the scroll to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement detailsButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(detailsButtonLocator));

        detailsButton.click();
    }

    public void clickSensorButton(){
        // Click sensor button
        WebElement sensorsButton = driver.findElement((By.id("select-sensors")));
        sensorsButton.click();
    }

    public void findSelectedSensors(){
        // Find selected sensors
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        List<WebElement> sensors = driver.findElements(By.className("checkbox"));
        for (WebElement s : sensors) {
            WebElement x = s.findElement(By.name("sensorCheckboxes"));
            if (x.getAttribute("checked").equals("true")) {
                s.findElement(By.tagName("label")).click();
            }
        }
    }

    public void saveChanges(){
        // Click save changes
        driver.findElement(By.id("saveSensors")).click();
    }

    public void selectLanguage(String language) {
        // Find the language dropdown button and click on it
        WebElement languageDropdownButton = driver.findElement(languageDropdownButtonLocator);
        languageDropdownButton.click();

        // Wait for the dropdown menu to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement dropdownMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(languageDropdownMenuLocator));

        // Find and click on the language option you want to select
        WebElement languageOption = dropdownMenu.findElement(By.xpath("//a[@href='/?lang=" + language + "']")); // Change the href value as needed
        languageOption.click();
    }

    public String getTranslation(String language){
        if(language.equals("en")){
            return "About\n" +
                    "Explore Data\n" +
                    "Participate\n" +
                    "App Store\n" +
                    "Play Store\n" +
                    "Register\n" +
                    "Log In\n" +
                    "EN";
        }
        else if(language.equals("rs")){
            return "O projektu\n" +
                    "Istraži podatke\n" +
                    "Učestvuj\n" +
                    "App Store\n" +
                    "Play Store\n" +
                    "Registracija\n" +
                    "Prijava\n" +
                    "RS";
        }
        else if(language.equals("de")){
            return "Über\n" +
                    "Daten erkunden\n" +
                    "Teilnehmen\n" +
                    "App Store\n" +
                    "Play Store\n" +
                    "Registrieren\n" +
                    "Anmelden\n" +
                    "DE";
        }
        else if(language.equals("ro")){
            return "Despre\n" +
                    "Explorați datele\n" +
                    "Participa\n" +
                    "App Store\n" +
                    "Play Store\n" +
                    "Inregistrează-te\n" +
                    "Autentificare\n" +
                    "RO";
        }
        else{
            return "За проектот\n" +
                    "Пребарај податоци\n" +
                    "Учествувај\n" +
                    "App Store\n" +
                    "Play Store\n" +
                    "Регистрација\n" +
                    "Најава\n" +
                    "MK";
        }
    }

    public LocalDateTime getDateTime() {
        String date = driver.findElement(dateLocator).getText();
        String time = driver.findElement(timeLocator).getText();

        String dateTime = date + " " + time;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }
}
