package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class MBExpensesPage {
    WebDriver driver;

    public MBExpensesPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);


    }

    @FindBy(id = "expenseSearchFilterPanel")
    public WebElement expensesFilter;

    @FindBy(xpath = "//table[@id = 'expenses-table']//th")
    public List<WebElement> expHeaders;

    @FindBy(xpath = "//div[@class='dt-buttons']/a[6]")
    public WebElement dropdown;

    @FindBy(xpath = "//*[@class='dt-button-collection']/a")
    public List<WebElement> dropElements;
}
