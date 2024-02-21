import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.scooter.pageObject.HomePage;
import ru.yandex.scooter.pageObject.TrackPage;
import java.util.concurrent.TimeUnit;


public class NotFoundOrderTest {
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
        driver.quit();
    }

    @Test
    public void isMistakeTextCorrectInFieldName() {
        //создаю объект класса главной страницы
        HomePage objHomePage = new HomePage(driver);
        //клик по кнопке Статус заказа
        objHomePage.statusOrderButtonClick();
        objHomePage.setEnterNumberOrderField("564654678");
        objHomePage.goButtonClick();
        //создаю объект класса страницы трекинга заказа
        TrackPage objTrackPage = new TrackPage(driver);
        //проверяю, что элемент "такого заказа нет" отобразился на странице
        objTrackPage.isNotFoundImageVisible();
    }
}
