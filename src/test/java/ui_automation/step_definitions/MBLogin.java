package ui_automation.step_definitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import ui_automation.pages.MBHomePage;
import ui_automation.pages.MBLoginPage;
import ui_automation.utilities.ConfigurationReader;
import ui_automation.utilities.Driver;

import javax.management.MBeanAttributeInfo;

public class MBLogin {
    MBLoginPage logObj = new MBLoginPage();
    MBHomePage homeObj = new MBHomePage();


    @Given("I’m on {string}")
    public void i_m_on(String loginPage) {
        Driver.getDriver().get(ConfigurationReader.getProperty("ui-config.properties", loginPage));
    }


    @Given("I enter User Name {string}")
    public void i_enter_User_Name(String userName) {
        logObj.userNameBox.sendKeys(ConfigurationReader.getProperty("ui-config.properties", userName));
    }

    @Given("I enter Password {string}")
    public void i_enter_Password(String password) {
        logObj.passwordBox.sendKeys(ConfigurationReader.getProperty("ui-config.properties", password));

    }

    @When("I click log in button")
    public void i_click_log_in_button() {

        logObj.loginButton.click();
    }

    @Then("I land on {string}")
    public void i_land_on(String homePage) {
        Assert.assertTrue(homeObj.dashBoard.isDisplayed());

    }

    @Then("I receive Login Failed message box")
    public void i_receive_Login_Failed_message_box() {
        Assert.assertEquals("Login failed!", logObj.errorMsg.getText());
    }

    @Then("I can click on OK button to get back to login screen")
    public void i_can_click_on_OK_button_to_get_back_to_login_screen() {
        logObj.OKButton.click();
        Assert.assertTrue(logObj.loginButton.isDisplayed());
    }

    @Given("I enter invalid User Name {string}")
    public void i_enter_invalid_User_Name(String userName) {
        logObj.userNameBox.sendKeys(userName);
    }

    @Given("I enter invalid Password {string}")
    public void i_enter_invalid_Password(String password) {
        logObj.passwordBox.sendKeys(password);
    }
}
