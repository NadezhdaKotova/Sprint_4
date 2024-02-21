import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.scooter.pageObject.HomePage;
import ru.yandex.scooter.pageObject.OrderPage;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class OrderScooterTest {
    WebDriver driver;

    private final String buttonLocation;
    private final String name;
    private final String familyName;
    private final String address;
    private final String subwayStation;
    private final String phoneNumber;
    private final String newDate;
    private final int days;
    private final String scooterColour;
    private final String newComment;
    //конструктор тест-класса
    public OrderScooterTest(String buttonLocation, String name, String familyName, String address, String subwayStation, String phoneNumber, String newDate, int days, String scooterColour, String newComment) {
        this.buttonLocation = buttonLocation;
        this.name = name;
        this.familyName = familyName;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phoneNumber = phoneNumber;
        this.newDate = newDate;
        this.days = days;
        this.scooterColour = scooterColour;
        this.newComment = newComment;
    }

    //тестовые данные
    @Parameterized.Parameters
    public static Object[][] getTestOrderData() {
        return new Object[][] {
                {"up","Владимир","Ленин","Москва, площадь Красная, 9","Охотный ряд","+74956235527","15.03.2024",5,"серая безысходность","У входа два мужика - не бойтесь, идите к двери и стучите громко. Я плохо слышу"},
                {"middle","Александр","Грибоедов","Москва, Чистопрудный бульвар","Чистые пруды","+74952000202","10.03.2024",1,"черный жемчуг","Оставьте у моего памятника, я вечером заберу"},
        };
    }

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
    public void isPopupAppeared(){
        //создаю объект класса главной страницы
        HomePage objHomePage = new HomePage(driver);
        //выбираю на какую кнопку "Заказать" давить - на верхнюю up или в центре страницы middle
        objHomePage.chooseOrderButtonAndClick(buttonLocation);
        //создаю объект страницы OrderPage
        OrderPage objOrderPage = new OrderPage(driver);
        //заполняю поля формы
        objOrderPage.fillFieldsInFormOrder(name,familyName,address,subwayStation,phoneNumber);
        //давлю на кнопку Далее
        objOrderPage.clickButtonFurther();
        //заполняю поля формы Про аренду
        objOrderPage.fillFieldsInFormAboutRent(newDate,days,scooterColour,newComment);
        //давлю на кнопку Заказать
        objOrderPage.clickButtonOrder();
        //давлю на кнопку "Да" в окне подтверждения заказа
        objOrderPage.clickYesButton();
        //проверяю, что окно "Оформить заказ" отобразилось на экране
        objOrderPage.orderIsProcessedDisplayed();
    }
}
