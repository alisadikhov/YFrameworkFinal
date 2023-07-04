package ui_automation.step_definitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import ui_automation.pages.OHRMEmployeesPage;
import ui_automation.pages.OHRMLoginPage;

import ui_automation.utilities.*;

import java.util.concurrent.TimeUnit;

public class OhrmEmployeesStep {
    OHRMLoginPage OHRMLoginPage = new OHRMLoginPage();
    OHRMEmployeesPage empPageObj = new OHRMEmployeesPage();

    @Given("I'm on logged in to OrangeHRM as admin")
    public void i_m_on_logged_in_to_OrangeHRM_as_admin() {
        Driver.getDriver().get(ConfigurationReader.getProperty("ui-config.properties", "yollhrm.url"));
        OHRMLoginPage.userNameInput.sendKeys(ConfigurationReader.getProperty("ui-config.properties", "yollhrm.username"));
        OHRMLoginPage.passwordInput.sendKeys(ConfigurationReader.getProperty("ui-config.properties", "yollhrm.password"));
        OHRMLoginPage.loginBtn.click();
    }


    @Given("I click on PIM>Add Employee")
    public void i_click_on_PIM_Add_Employee() {
        empPageObj.pimTab.click();
        empPageObj.addEmp.click();
    }

    @Then("I feel out {string},{string}")
    public void i_feel_out(String name, String lname) {
        empPageObj.empFName.sendKeys("ooo");
        empPageObj.empLName.sendKeys("lll");

    }

    @When("I click on {string}")
    public void i_click_on(String string) {
        empPageObj.checkBox.click();
        Driver.getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Then("I see additional fields displayed")
    public void i_see_additional_fields_displayed() {
        for (WebElement element : empPageObj.extraInfo) {
            Assert.assertTrue(element.isDisplayed());
        }
    }

    @When("The fields are no longer displayed")
    public void the_fields_are_no_longer_displayed() {
        for (WebElement element : empPageObj.extraInfo) {
            Assert.assertFalse(element.isDisplayed());
        }
    }


}
