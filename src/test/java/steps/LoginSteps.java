package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import com.nathan.pages.LoginPage;
import com.nathan.steps.Hooks;

import static org.junit.Assert.assertTrue;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("user is on the SauceDemo login page")
    public void userIsOnTheSauceDemoLoginPage() {
        // Ensure driver is initialized from Hooks
        WebDriver driver = Hooks.driver;

        // Add null check and error handling
        if (driver == null) {
            throw new RuntimeException("WebDriver not initialized by Hooks. Check @Before method execution order.");
        }

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
        assertTrue(Hooks.driver.getCurrentUrl().contains("inventory.html"));
    }

    @Then("an error message {string} should be displayed")
    public void anErrorMessageShouldBeDisplayed(String expectedError) {
        String actualError = loginPage.getErrorText();
        assertTrue(actualError.contains(expectedError));
    }
}