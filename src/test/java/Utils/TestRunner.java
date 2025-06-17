package Utils;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@CucumberOptions(
        features = "src/test/java/features/apiTesting.feature",
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

//        private Process fastAPIProcess;
//
//        @BeforeSuite
//        public void startFastAPI() {
//                try {
//                        System.out.println("🚀 Starting FastAPI server...");
//
//                        String command = "cmd /c start python -m uvicorn app:app --host 127.0.0.1 --port 8000 --reload";
//                        fastAPIProcess = Runtime.getRuntime().exec(command);
//
//                        Thread.sleep(15000); // Allow FastAPI to start
//
//                        // Verify API is accessible
//                        if (!isAPIRunning("http://127.0.0.1:8000/health")) {
//                                throw new IOException("❌ FastAPI is not responding!");
//                        }
//
//                        System.out.println("✅ FastAPI is running successfully!");
//
//                } catch (IOException | InterruptedException e) {
//                        System.err.println("❌ FastAPI failed to start.");
//                        e.printStackTrace();
//                }
//        }
//
//        private boolean isAPIRunning(String urlString) {
//                try {
//                        URL url = new URL(urlString);
//                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                        connection.setRequestMethod("GET");
//                        connection.connect();
//                        return connection.getResponseCode() == 200;
//                } catch (Exception e) {
//                        return false;
//                }
//        }

//        @AfterSuite
//        public void stopFastAPI() {
//                try {
//                        System.out.println("🛑 Stopping FastAPI server...");
//                        Runtime.getRuntime().exec("cmd /c taskkill /F /IM uvicorn.exe");
//                } catch (IOException e) {
//                        System.err.println("❌ Failed to stop FastAPI.");
//                        e.printStackTrace();
//                }
//        }

//        @AfterSuite
//        public void generateAllureReport() {
//                try {
//                        System.out.println("📊 Generating Allure Report...");
//                        Process generateReport = Runtime.getRuntime().exec("cmd /c allure generate target/allure-results --clean -o test-output/allure-report");
//                        generateReport.waitFor();
//
//                        System.out.println("🌐 Serving Allure Report...");
//                        Process serveReport = Runtime.getRuntime().exec("cmd /c allure serve test-output/allure-report");
//                        serveReport.waitFor();
//
//                        System.out.println("✅ Allure Report Hosted Successfully!");
//
//                } catch (IOException | InterruptedException e) {
//                        System.err.println("❌ Error generating Allure report.");
//                        e.printStackTrace();
//                }
//        }
}