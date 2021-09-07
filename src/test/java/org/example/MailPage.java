package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MailPage {

    public WebDriver driver;
    public MailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    //кнопка "Написать письмо"
    @FindBy(xpath = "//*[contains(@class, 'mail-ComposeButton js-main-action-compose')]")
    private WebElement writeMessageBtn;

    //тело письма
    @FindBy(xpath = "//*[contains(@class, 'cke_wysiwyg_div cke_reset cke_enable_context_menu cke_editable " +
            "cke_editable_themed cke_contents_ltr cke_htmlplaceholder')]")
    private WebElement textArea;

    //кнопка после ввода получателя типа подвердить получателя
    @FindBy(xpath = "//*[contains(@class, 'mail-Page-Body js-mail-Page-Body pointerfocus')]")
    private WebElement acceptRecipient;

    //кнопка "Отправить"
    @FindBy(xpath = "//*[contains(@class, 'control button2 button2_view_default button2_tone_default button2_size_l " +
            "button2_theme_action button2_pin_circle-circle ComposeControlPanelButton-Button ComposeControlPanelButton-Button_action ComposeControlPanelButton-Button_sidePadding')]")
    private WebElement sendBtn;

    public String getCountMessage(){
        List<WebElement> elements;
        elements = driver.findElements(By.xpath("//*[contains(text(), 'Simbirsoft Тестовое задание')]"));
        System.out.println(elements.size());
        return String.valueOf(elements.size());
    }

    public void clickWriteMessageBtn() {
        writeMessageBtn.click(); }

    public void inputRecipient(String recipient){
        driver.findElement(By.xpath("//*[contains(@class, 'composeYabbles')]")).sendKeys(recipient);
    }

    public void inputTheme(){
        driver.findElement(By.xpath("//*[contains(@class, 'composeTextField ComposeSubject-TextField')]")).sendKeys
                ("Simbirsoft Тестовое задание. Калинин");
    }

    public void clickAcceptRecipient(){
        acceptRecipient.click();
    }

    public void clickTextArea(){
        textArea.click();
    }

    public void inputText(String text){
        driver.findElement(By.xpath("//*[contains(@class, 'cke_wysiwyg_div cke_reset cke_enable_context_menu cke_editable" +
                " cke_editable_themed cke_contents_ltr cke_htmlplaceholder')]")).sendKeys("Количество писем с темой 'Simbirsoft Тестовое задание': " + text);
    }

    public void clickSendBtn(){
        sendBtn.click();
    }
}
