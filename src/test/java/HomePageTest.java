import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.example.pages.HomePage;
import org.example.utils.WebDriverFactory;

public class HomePageTest {
    WebDriver driver;
    HomePage homePage;

    @BeforeEach
    public void SetUp(){
        driver = WebDriverFactory.createDriver();
        driver.get("https://pulse.eco/");
        homePage = new HomePage(driver);
    }

    @Test //Student 1
    public void testMapRedirection() {
        try{
            String country = "Macedonia";
            String city = "Bitola";

            System.out.println("Testing city selection for " + city + ", " + country);

            // Click on the dropdown to open the list of countries
            homePage.clickDropdown();

            // Find the country element and hover over it
            homePage.moveToCountry(country);

            // Find and click on the city
            homePage.clickCity(city);

            // Click "View Map" button
            homePage.clickViewMapButton();

            // Assert redirection to city
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = city.toLowerCase() + ".pulse.eco";

            Assert.assertTrue(currentUrl.contains(expectedUrl));
        } catch (Exception e) {
            System.out.println("Exception");
            System.out.println(e);
        } finally {
            driver.quit();
        }
    }

    @Test // Student 1
    public void testCityViewAppearsOnMap() {
        try {
            String country = "Macedonia";
            String city = "Bitola";

            System.out.println("Testing city view appears on map for " + city + ", " + country);

            // navigate to the city
            homePage.clickDropdown();
            homePage.moveToCountry(country);
            homePage.clickCity(city);
            homePage.clickViewMapButton();

            //verify that the map with a city view is displayed on screen
            Assert.assertTrue(homePage.isMapDisplayed(), "Map is not displayed");
        } catch (Exception e) {
            System.out.println("Exception");
            System.out.println(e);
        } finally {
            driver.quit();
        }
    }
}
