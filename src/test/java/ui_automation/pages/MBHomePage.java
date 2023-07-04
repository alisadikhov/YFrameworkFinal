package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

public class MBHomePage {
    WebDriver driver;

    public MBHomePage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#navbar-second-toggle [href='/Home']")
    public WebElement dashBoard;

    @FindBy(css = "#navbar-second-toggle [href='/PersonalInfo']")
    public WebElement personalDetailsTab;

    @FindBy(css = ".navbar-boxed [href ='/Expenses']")
    public WebElement expensesTab;
}
