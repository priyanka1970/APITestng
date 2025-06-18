package Utils;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;

import java.io.IOException;

@CucumberOptions(
        features = "src/test/java/features",
        glue = "features.steps",
        plugin = {
                "pretty",
                "json:target/cucumber.json",
                "html:test-output/cucumber-html-report",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
