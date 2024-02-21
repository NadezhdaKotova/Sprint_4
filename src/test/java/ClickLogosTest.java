import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.scooter.pageObject.HomePage;
import ru.yandex.scooter.pageObject.YandexDzen;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ClickLogosTest {
    WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //создаю объект класса главной страницы
        HomePage objHomePage = new HomePage(driver);
        //открываю главную страницу
        objHomePage.openScooterPage();
        //если кнопка согласия кук появилась, давлю на нее
        objHomePage.acceptCookies();

    }

    //закрываю браузер
    @After
    public void tearDown() {
        //makeScreenshot();
        driver.quit();
    }

    @Test
    public void isLinkFromLogoSamokatCorrect() {
        //создаю объект класса главной страницы
        HomePage objHomePage = new HomePage(driver);
        //кликаю по логотипу Самоката
        objHomePage.samokatLogoClick();
        //сравниваю результат отображения
        assertEquals("https://qa-scooter.praktikum-services.ru/",driver.getCurrentUrl());
    }
    @Test
    public void isLinkFromLogoYandexCorrect() {
        //создаю объект класса главной страницы
        HomePage objHomePage = new HomePage(driver);
        //кликаю по логотипу Яндекса
        objHomePage.yandexLogoClick();
        //переключаюсь в новую вкладку
        Set<String> tab_handles = driver.getWindowHandles();
        driver.switchTo().window(tab_handles.toArray()[1].toString());
        //создаю объект класса Страницы Яндекс-Дзена
        YandexDzen objYandexDzen = new YandexDzen(driver);
        //Ожидаю, когда она загрузится полностью (экcпериментально, чтобы не забыть потом как этом делается)
        objYandexDzen.waitForLoad(driver);
        //сравниваю результат - поскольку в задании указан Яндекс, а сейчас с www.yandex.ru стоит редирект на https://dzen.ru/, проверяю страницу Дзена
        assertEquals("https://dzen.ru/?yredirect=true", driver.getCurrentUrl());
    }
}
