package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    public static WebDriver createDriver() {
        WebDriver driver = null;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1400,800");
        options.addArguments("--headless");


        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver(options);

        return driver;
    }
}
