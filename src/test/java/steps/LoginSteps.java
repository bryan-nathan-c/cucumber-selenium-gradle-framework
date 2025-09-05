package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.nathan.pages.LoginPage;

import static org.junit.Assert.assertTrue;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;

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
    public void user_is_on_the_sauce_demo_login_page() {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @When("username {string} and password {string} are entered")
    public void username_and_password_are_entered(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("the login button is clicked")
    public void the_login_button_is_clicked() {
        loginPage.clickLogin();
    }

    @Then("user should be redirected to the Products page")
    public void user_should_be_redirected_to_the_products_page() {
        assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Then("an error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String expectedError) {
        String actualError = loginPage.getErrorText();
        assertTrue(actualError.contains(expectedError));
    }
}
