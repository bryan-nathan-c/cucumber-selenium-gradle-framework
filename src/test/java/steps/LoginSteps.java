package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import com.nathan.pages.LoginPage;
import com.nathan.steps.Hooks;

import static org.junit.Assert.assertTrue;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    // Remove @Before and @After - let Hooks.java handle WebDriver setup
    // This prevents duplicate WebDriver instances

    @Given("user is on the SauceDemo login page")
    public void userIsOnTheSauceDemoLoginPage() {
        // Use driver from Hooks.java
        driver = Hooks.driver;
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