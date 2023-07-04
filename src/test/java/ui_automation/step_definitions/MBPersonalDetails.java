package ui_automation.step_definitions;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui_automation.pages.MBHomePage;
import ui_automation.pages.MBLoginPage;
import ui_automation.pages.MbPersonalDetailsPage;
import ui_automation.utilities.ConfigurationReader;
import ui_automation.utilities.Driver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class MBPersonalDetails {
    WebDriver driver;
    MBLoginPage logObj = new MBLoginPage();
    MBHomePage homeObj = new MBHomePage();
    MbPersonalDetailsPage persDetObj = new MbPersonalDetailsPage();


    @Given("I am logged in to application")
    public void i_am_logged_in_to_application() {
        Driver.getDriver().get(ConfigurationReader.getProperty("ui-config.properties", "MealB.loginpage"));
        logObj.userNameBox.sendKeys(ConfigurationReader.getProperty("ui-config.properties", "MealB.empUserName"));
        logObj.passwordBox.sendKeys(ConfigurationReader.getProperty("ui-config.properties", "MealB.empPassword"));
        logObj.loginButton.click();
        Driver.getDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

    }

    @Given("I click on personal Details tab")
    public void i_click_on_personal_Details_tab() {
        homeObj.personalDetailsTab.click();
    }

    @Given("I am on Personal Details page")
    public void i_am_on_Personal_Details_page() {
        Assert.assertTrue(persDetObj.pageTitle.isDisplayed());
    }

    @Then("I see all the required fields pre-filled")
    public void i_see_all_the_required_fields_pre_filled(DataTable data) {

        List<Map<String, String>> maps = data.asMaps(String.class, String.class);
        int i = 0;
        for (Map<String, String> map : maps) {

            if ((persDetObj.requiredFields.get(i).getAttribute("name")).equals(map.get("fields"))) {
                Assert.assertNotNull(persDetObj.requiredFields.get(i).getAttribute("value"));
            }
            i++;
        }

    }

    @Then("field is displayed with value as read only")
    public void field_is_displayed_with_value_as_read_only(DataTable data) {
        List<Map<String, String>> maps = data.asMaps(String.class, String.class);
        int i = 0;
        for (Map<String, String> map : maps) {
            for (int j = 0; j < persDetObj.requiredFields.size(); j++) {

                if ((persDetObj.requiredFields.get(j).getAttribute("name")).equals(map.get("field"))) {
                    Assert.assertFalse(persDetObj.requiredFields.get(j).isEnabled());
                }

            }
        }
    }


    @Given("I don’t have values for all {string}")
    public void i_don_t_have_values_for_all(String field) {
        for (int i = 0; i < persDetObj.requiredFields.size(); i++) {
            if ((persDetObj.requiredFields.get(i).getAttribute("name")).equals(field)) {
                persDetObj.requiredFields.get(i).clear();
            }
        }
    }


    @When("I click on the update button")
    public void i_click_on_the_update_button() {
        persDetObj.updateButton.click();
    }

    @Then("I get a “This field is required” message for each required field")
    public void i_get_a_This_field_is_required_message_for_each_required_field() {
        Assert.assertTrue(persDetObj.errorMessage.isDisplayed());
    }

    @Given("I enter new value {string} in password field")
    public void i_enter_new_value_in_password_field(String password) {
        persDetObj.pswrdInput.sendKeys(password);
    }

    @Given("I don’t enter same value {string} on confirm password field")
    public void i_don_t_enter_same_value_on_confirm_password_field(String differentPassword) {
        persDetObj.pswrdInputVerify.sendKeys(differentPassword);
    }

    @Then("I see message “Password confirmation doesn't match Password”")
    public void i_see_message_Password_confirmation_doesn_t_match_Password() {
        Assert.assertTrue(persDetObj.pswrdErrorMsg.isDisplayed());
    }

    @Then("I should be able to enter only {int} {string} in Phone Number field")
    public void i_should_be_able_to_enter_only_in_Phone_Number_field(int phNumLength, String phNum) {
        persDetObj.phoneNumBox.clear();
        persDetObj.phoneNumBox.sendKeys(phNum);
        persDetObj.updateButton.click();
        String output = persDetObj.phoneNumBox.getAttribute("value");
        output = output.replace("(", "");
        output = output.replace(")", "");
        output = output.replace("-", "");
        output = output.replace(" ", "");
        output = output.replace("_", "");
        Assert.assertEquals(phNumLength, output.length());

    }

    @Then("I should be able to enter only number {string} to the Postal Code field")
    public void i_should_be_able_to_enter_only_number_to_the_Postal_Code_field(String zipCodeInput) {
        persDetObj.zipCodeBox.clear();
        persDetObj.zipCodeBox.sendKeys(zipCodeInput);
        String output = persDetObj.zipCodeBox.getAttribute("value");
        Assert.assertEquals(zipCodeInput, output);
    }

}