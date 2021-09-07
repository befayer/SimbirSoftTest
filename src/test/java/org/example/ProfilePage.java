package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {

    public WebDriver driver;
    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    //субменю (правый верхний угол)
    @FindBy(xpath = "//*[contains(@class, 'user-account__name')]")
    private WebElement subMenuBtn;

    //кнопка "Почта"
    @FindBy(xpath = "//*[contains(@class, 'menu__text')]")
    private WebElement mailBtn;

    public void clickSubMenuBtn() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        subMenuBtn.click(); }

    public void clickmailBtn() {
        mailBtn.click(); }
}
