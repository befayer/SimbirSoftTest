package org.example;

import io.qameta.allure.*;
import org.junit.*;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.util.concurrent.TimeUnit;

public class LoginTest {

    private static LoginPage loginPage;
    private static MailPage mailPage;
    private static WebDriver driver;

    private static final String LOGIN = ConfigProperties.getProperty("login");
    private static final String PASSWORD = ConfigProperties.getProperty("password");
    private static final String MAIL = ConfigProperties.getProperty("recipientmail");

    @BeforeClass
    public static void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setPlatform(Platform.WINDOWS);
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("92.0.4515.107");
        WebDriver driver = new RemoteWebDriver(new URL(ConfigProperties.getProperty("urlfordriver")), capabilities);
        loginPage = new LoginPage(driver);
        mailPage = new MailPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigProperties.getProperty("loginpage"));
    }

    @Epic("Тестирование работы автотеста как тестового задания для Simbir Soft")
    @Description("Тест проверяет, что сообщения с темой есть") //
    @Test
    public void countMessageTest(){
        //
        mailPage.clickLoginBtn();
        //ввод логина
        loginPage.inputLogin(LOGIN);
        //"войти"
        loginPage.clickLoginBtn();
        //ввод пароля
        loginPage.inputPasswd(PASSWORD);
        //"войти"
        loginPage.clickLoginBtn();
        //считаем кол-во писем с требуемой темой "Simbirsoft Тестовое задание"
        String countOfMessage = mailPage.getCountMessage();
        Assert.assertNotNull(countOfMessage);
        //кнопка "Написать письмо"
        mailPage.clickWriteMessageBtn();
        //ввод адреса получателя
        mailPage.inputRecipient(MAIL);
        //клик по полю с темой письма
        mailPage.clickAcceptRecipient();
        //ввод темы письма
        mailPage.inputTheme();
        //клик по телу письма
        mailPage.clickTextArea();
        //ввод текста в тело письма
        mailPage.inputText(countOfMessage);
        //кнопка "Отправить"
        mailPage.clickSendBtn();
        //кнопка "Отправленные"
        mailPage.clickSentMailsBtn();
        //проверяем, отправилось ли письмо
        Assert.assertEquals("Письмо было отправлено", 1, mailPage.checkSentMessage(countOfMessage));
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
