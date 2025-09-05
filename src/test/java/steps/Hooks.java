package com.nathan.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Files;
import java.nio.file.Path;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()){
            try {
                byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "failed-screenshot");
                // optional: save file
                Files.write(Path.of("target/screenshots/" + System.currentTimeMillis() + ".png"), screenshot);
            } catch (Exception e){
                // ignore
            }
        }
        if (driver != null) driver.quit();
    }
}
