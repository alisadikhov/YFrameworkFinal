package runners;


import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber",
                "json:target/cucumber.json"},
        features = "src/test/resources/uiFeatures",
        glue = "ui_automation.step_definitions",
        tags = "@HRMTest",
        dryRun = false,
        strict = true,
        monochrome = true
)

public class UITestRunner {

}