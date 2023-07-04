package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class OHRMLoginPage {
    WebDriver driver;

    public OHRMLoginPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "txtUsername")
    public WebElement userNameInput;

    @FindBy(id = "txtPassword")
    public WebElement passwordInput;

    @FindBy(id = "btnLogin")
    public WebElement loginBtn;


}
