package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    protected WebDriver driver;

    protected LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(xpath = "//*[contains(@id, 'passp-field-login')]")
    private WebElement loginField;

    @FindBy(xpath = "//*[contains(@id, 'passp:sign-in')]")
    private WebElement loginBtn;

    @FindBy(xpath = "//*[contains(@id, 'passp-field-passwd')]")
    private WebElement passwordField;

    public void inputLogin(String login) { loginField.sendKeys(login); }
    public void inputPasswd(String password) { passwordField.sendKeys(password); }
    public void clickLoginBtn() { loginBtn.click(); }
}
