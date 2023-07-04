package ui_automation.step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui_automation.pages.MBExpensesPage;
import ui_automation.pages.MBHomePage;
import ui_automation.utilities.Driver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MBExpenses {
    WebDriver driver;
    MBHomePage homeObj = new MBHomePage();
    MBExpensesPage expObj = new MBExpensesPage();

    static final Logger oLog = LogManager.getLogger(MBExpenses.class);

    @When("I click on Expenses Tab")
    public void i_click_on_Expenses_Tab() {
        homeObj.expensesTab.click();
        oLog.info("something");

    }

    @Then("I am navigated to expenses page")
    public void i_am_navigated_to_expenses_page() {
        Assert.assertTrue(expObj.expensesFilter.isDisplayed());
        oLog.info("something else");
    }

    @Then("I see table with default expense details headers")
    public void i_see_table_with_default_expense_details_headers(DataTable dataTable) {
        List<Map<String, String>> headers = dataTable.asMaps(String.class, String.class);
        int i = 1;
        for (Map<String, String> element : headers) {
            Assert.assertTrue(expObj.expHeaders.get(i).getText().contains(element.get("headers")));
            i++;
        }

    }

    @When("I click on the List Dropdown")
    public void i_click_on_the_List_Dropdown() {
        expObj.dropdown.click();

    }

    @Then("I see list of all table headers")
    public void i_see_list_of_all_table_headers(DataTable dataTable) {
        List<Map<String, String>> dropdownHeaders = dataTable.asMaps(String.class, String.class);
        int j = 0;
        for (Map<String, String> options : dropdownHeaders) {
            Assert.assertEquals(expObj.dropElements.get(j).getText(), options.get("headers"));
            j++;
        }
    }

    @When("I unselect all the options from dropdown list")
    public void i_unselect_all_the_options_from_dropdown_list() {
        expObj.dropdown.click();
        for (int i = 0; i < expObj.dropElements.size(); i++) {
            expObj.dropElements.get(i).click();
            Driver.getDriver().manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

        }
    }

    @Then("Table headers are removed as well")
    public void table_headers_are_removed_as_well() {
        int headersSize = expObj.expHeaders.size();
        Assert.assertEquals(2, headersSize);
    }


}
