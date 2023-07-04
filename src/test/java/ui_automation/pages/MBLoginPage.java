package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

public class MBLoginPage {
    WebDriver driver;

    public MBLoginPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);

    }

    @FindBy(name = "usernameOrEmailAddress")
    public WebElement userNameBox;

    @FindBy(name = "Password")
    public WebElement passwordBox;

    @FindBy(id = "LoginButton")
    public WebElement loginButton;

    @FindBy(xpath = "//*[@class='sweet-alert showSweetAlert visible']/h2")
    public WebElement errorMsg;

    @FindBy(xpath = "//*[@class='confirm']")
    public WebElement OKButton;

}
