package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class OHRMEmployeesPage {
    WebDriver driver;

    public OHRMEmployeesPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "menu_pim_viewPimModule")
    public WebElement pimTab;

    @FindBy(id = "menu_pim_addEmployee")
    public WebElement addEmp;

    @FindBy(id = "firstName")
    public WebElement empFName;


    @FindBy(id = "lastName")
    public WebElement empLName;

    @FindBy(id = "chkLogin")
    public WebElement checkBox;

    @FindBy(className = "loginSection")
    public List<WebElement> extraInfo;
}
