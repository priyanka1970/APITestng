package Utils;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@CucumberOptions(
        features = "src/test/java/features", // Now picks all .feature files
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

        @BeforeSuite
        public void checkFastApiHealth() throws IOException {
                String healthCheckUrl = "http://127.0.0.1:8000/health"; // Replace if using ConfigUtil
                HttpURLConnection connection = (HttpURLConnection) new URL(healthCheckUrl).openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int code = connection.getResponseCode();
                if (code != 200) {
                        throw new RuntimeException("❌ FastAPI server is not running (status code: " + code + ")");
                } else {
                        System.out.println("✅ FastAPI is up and healthy (status code: 200).");
                }
        }
}
