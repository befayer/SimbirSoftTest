package org.example;

import io.qameta.allure.*;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    public static LoginPage loginPage;
    public static MailPage mailPage;
    public static WebDriver driver;
    public static ProfilePage profilePage;

    public static final String LOGIN = "simbirsofttestapp";
    public static final String PASSWORD = "helloiampassword";
    public static final String MAIL = "simbirsofttestapp@yandex.ru";


    @BeforeClass
    public static void setup() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //DesiredCapabilities cap = DesiredCapabilities.chrome();
        capabilities.setPlatform(Platform.WINDOWS);
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("92.0.4515.107");
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.105:5558/wd/hub"), capabilities);
        //System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\chromedriver\\chromedriver.exe");
        //driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        mailPage = new MailPage(driver);
        profilePage = new ProfilePage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://passport.yandex.ru/auth");
    }

    @Epic("Тестирование работы автотеста как тестового задания для Simbir Soft")
    @Description("Тест проверяет, что сообщения с темой есть") //
    @Test
    public void countMessageTest(){


        //ввод логина
        loginPage.inputLogin(LOGIN);
        //"войти"
        loginPage.clickLoginBtn();
        //ввод пароля
        loginPage.inputPasswd(PASSWORD);
        //"войти"
        loginPage.clickLoginBtn();
        //кнопка выпадающего меню
        profilePage.clickSubMenuBtn();
        //кнопка почты
        profilePage.clickmailBtn();
        //считаем кол-во писем с требуемой темой "Simbirsoft Тестовое задание"
        String countOfMessage = mailPage.getCountMessage();
        //
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
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
