package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;


import java.util.List;

public class MbPersonalDetailsPage {
    WebDriver driver;


    public MbPersonalDetailsPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "form-label")
    public List<WebElement> fieldLabels;

    @FindBy(xpath = "//*[text() =' Personal details']")
    public WebElement pageTitle;

    @FindBy(xpath = "//*[@aria-required = 'true']")
    public List<WebElement> requiredFields;

    @FindBy(id = "RegisterButton")
    public WebElement updateButton;

    @FindBy(id = "PhoneNumber")
    public WebElement phoneNumBox;

    @FindBy(name = "PostalCode")
    public WebElement zipCodeBox;

    @FindBy(className = "validation-error-label")
    public WebElement errorMessage;

    @FindBy(name = "Password")
    public WebElement pswrdInput;

    @FindBy(name = "PasswordConfirmation")
    public WebElement pswrdInputVerify;

    @FindBy(id = "PasswordConfirmation-error")
    public WebElement pswrdErrorMsg;
}
