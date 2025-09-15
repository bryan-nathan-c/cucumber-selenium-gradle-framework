package steps;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",      // lokasi .feature
        glue = "steps",                       // lokasi step definitions
        plugin = {
                "pretty",
                "html:build/cucumber-reports.html",   // HTML report
                "json:build/cucumber-reports.json",   // JSON report
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"   // Allure plugin
        },
        monochrome = true
)
public class RunCucumberTest {
}
