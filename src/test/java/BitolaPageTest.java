import org.example.pages.BitolaPage;
import org.example.utils.WebDriverFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BitolaPageTest {
    WebDriver driver;
    BitolaPage bitolaPage;

    @BeforeEach
    public void SetUp(){
        driver = WebDriverFactory.createDriver();
        driver.get("https://bitola.pulse.eco/");
        bitolaPage = new BitolaPage(driver);
    }


    @Test //Student 2
    public void test1DetailedView() {
        try {
            bitolaPage.clickDetailsButton();

//      check if redirected to detailed view
            assert driver.findElement(By.id("from")).isDisplayed();

        } catch (Exception e) {
            System.out.println("================= Exception =================");
            System.out.println(e);
            System.out.println("=============================================");
        } finally {
            driver.quit();
        }
    }

    @Test //Student 3
    public void test2DetailedView(){
        bitolaPage.clickDetailsButton();

        WebElement spanElement = driver.findElement(By.cssSelector("span.sideSubtitle"));
        String actualText = spanElement.getText();

        assertNotEquals("Past 24 hours", actualText);

        assert driver.findElement(By.id("from")).isDisplayed();
        assert driver.findElement(By.id("to")).isDisplayed();

        String url = driver.getCurrentUrl();
    }

    @Test //Student 2
    public void testUnselectingSensors() {
        try {

            Actions actions = new Actions(driver);

            // Navigate to bitola page
            driver.get("https://bitola.pulse.eco/");

            // Verify bitola page
            WebElement detailsBtn = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div[2]/div[1]/div[4]/div[1]/div/button"));
            if (!detailsBtn.isDisplayed()) {
                assert false;
            }

            bitolaPage.clickSensorButton();
            bitolaPage.findSelectedSensors();
            bitolaPage.saveChanges();

            // If we unselect all sensors, this list under the graph should be empty
            assert driver.findElements(By.className("sensor-desc")).isEmpty();

        } catch (Exception e) {
            System.out.println("================= Exception =================");
            System.out.println(e);
            System.out.println("=============================================");
        } finally {
            driver.quit();
        }
    }

    @Test //Student 1
    public void testCurrentDateTime() {
        System.out.println("Testing current date and time is displayed");
        try {
            LocalDateTime displayedDateTime = bitolaPage.getDateTime();
            LocalDateTime currentDateTime = LocalDateTime.now();

            long acceptableTimeDiff = 5;
            long actualTimeDiff = Math.abs(Duration.between(displayedDateTime, currentDateTime).toMinutes());

            Assertions.assertTrue(actualTimeDiff <= acceptableTimeDiff);
        } catch (Exception e) {
            System.out.println("Exception");
            System.out.println(e);
        } finally {
            driver.quit();
        }
    }

    @ParameterizedTest //Student 3
    @MethodSource("languageData")
    public void testLanguageChange(String language) {

        if(language.equals("mk")){
            driver.get("https://bitola.pulse.eco/?lang=en");
        }

        System.out.println("Testing for language " + language);

        BitolaPage bitolaPage = new BitolaPage(driver);

        bitolaPage.selectLanguage(language);

        WebElement navbar = driver.findElement(By.className("navbar-nav"));

        Assertions.assertEquals(navbar.getText(), bitolaPage.getTranslation(language));

        driver.quit();
    }

    private static Stream<String> languageData() {
        return Stream.of("en", "rs", "de", "ro", "mk");
    }
}
