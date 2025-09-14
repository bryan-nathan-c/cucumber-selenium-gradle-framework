package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Files;
import java.nio.file.Path;

public class Hooks {
    public static WebDriver driver;

    @Before(order = 0)  // Ensure this runs first before any other @Before methods
    public void setUp(){
        System.out.println("Setting up WebDriver...");

        // Configure Chrome for headless CI/CD environment
        ChromeOptions options = new ChromeOptions();

        // Essential options for GitHub Actions
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-web-security");
        options.addArguments("--window-size=1920,1080");

        // Set Chrome binary path for GitHub Actions
        String chromeBin = System.getenv("CHROME_BIN");
        if (chromeBin != null) {
            options.setBinary(chromeBin);
        }

        driver = new ChromeDriver(options);
        System.out.println("WebDriver initialized successfully");
    }

    @After(order = 1000)  // Ensure this runs last after all other @After methods
    public void tearDown(Scenario scenario){
        System.out.println("Tearing down WebDriver...");

        if (scenario.isFailed()){
            try {
                byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "failed-screenshot");
                // Save to build folder for GitHub Actions artifacts
                Files.createDirectories(Path.of("build/screenshots"));
                Files.write(Path.of("build/screenshots/" + System.currentTimeMillis() + ".png"), screenshot);
                System.out.println("Screenshot saved for failed scenario");
            } catch (Exception e){
                System.out.println("Screenshot failed: " + e.getMessage());
            }
        }

        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("WebDriver closed successfully");
        }
    }
}