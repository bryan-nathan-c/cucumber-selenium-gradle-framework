package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.nathan.pages.LoginPage;

import static org.junit.Assert.assertTrue;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("user is on the SauceDemo login page")
    public void userIsOnTheSauceDemoLoginPage() {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @When("username {string} and password {string} are entered")
    public void usernameAndPasswordAreEntered(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("the login button is clicked")
    public void theLoginButtonIsClicked() {
        loginPage.clickLogin();
    }

    @Then("user should be redirected to the Products page")
    public void userShouldBeRedirectedToTheProductsPage() {
        assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Then("an error message {string} should be displayed")
    public void anErrorMessageShouldBeDisplayed(String expectedError) {
        String actualError = loginPage.getErrorText();
        assertTrue(actualError.contains(expectedError));
    }
}