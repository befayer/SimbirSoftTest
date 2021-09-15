package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class MailPage {

    public WebDriver driver;
    public MailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    //кнопка "Войти"
    @FindBy(xpath = "//*[contains(@class, 'HeadBanner-Button-Enter with-shadow')]")
    private WebElement loginBtn;

    //кнопка "Написать письмо"
    @FindBy(xpath = "//*[contains(@class, 'js-main-action-compose')]")
    private WebElement writeMessageBtn;

    //тело письма
    @FindBy(xpath = "//*[contains(@class, 'cke_enable_context_menu')]")
    private WebElement textArea;

    //кнопка после ввода получателя типа подвердить получателя
    @FindBy(xpath = "//*[contains(@class, 'ContactsSuggestItemDesktop')]")
    private WebElement acceptRecipient;

    //кнопка "Отправленные"
    @FindBy(xpath = "//html/body/div[3]/div[7]/div/div[3]/div[2]/div[3]/div/div[1]/div[5]/a[1]")
    private WebElement sentMailsBtn;

    //лейбл кол-во писем
    @FindBy(xpath = "///html/body/div[3]/div[7]/div/div[3]/div[2]/div[3]/div/div[1]/div[5]/a[1]/div/span")
    private WebElement countMailsLbl;

    //кнопка "Отправить"
    @FindBy(xpath = "//*[contains(@class, 'Button2_pin_circle-circle')]")
    private WebElement sendBtn;

    //кнопка "Обновить"
    @FindBy(xpath = "//*[contains(@class, 'js-main-action-refresh')]")
    private WebElement updateBtn;

    //метод для подсчета писем с искомой темой
    public String getCountMessage(){
        List<WebElement> elements;
        elements = driver.findElements(By.xpath("//*[contains(text(), 'Simbirsoft Тестовое задание')]"));
        return String.valueOf(elements.size());
    }

    //кол-во отправленных
    public int countSentMessage(){
        int count;
        String countString;
        countString = driver.findElement(By.xpath
                ("/html/body/div[3]/div[7]/div/div[3]/div[2]/div[3]/div/div[1]/div[5]/a[1]/div/span")).getAttribute("innerHTML");
        count = Integer.parseInt(countString);
        return count;
    }

    public void inputRecipient(String recipient){
        driver.findElement(By.xpath("//*[contains(@class, 'composeYabbles')]")).sendKeys(recipient);
    }

    public void inputTheme(){
        driver.findElement(By.xpath("//*[contains(@class, 'composeTextField ComposeSubject-TextField')]")).sendKeys
                ("Simbirsoft Тестовое задание. Калинин");
    }

    public void clickAcceptRecipient(){ acceptRecipient.click(); }

    public void clickTextArea(){ textArea.click(); }

    public void inputText(String text){
        driver.findElement(By.xpath("//*[contains(@class, 'cke_htmlplaceholder')]")).sendKeys
                ("Количество писем с темой: " + text);
    }

    public void clickLoginBtn(){ loginBtn.click(); }

    public void clickSentMailsBtn(){ sentMailsBtn.click(); }

    public void clickUpdateBtn(){ updateBtn.click(); }

    public void clickWriteMessageBtn() { writeMessageBtn.click(); }

    public void clickSendBtn(){ sendBtn.click(); }
}
