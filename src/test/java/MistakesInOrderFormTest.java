import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.scooter.pageObject.HomePage;
import ru.yandex.scooter.pageObject.OrderPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class MistakesInOrderFormTest {
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
    public void isMistakeTextCorrectInFieldName(){
        //создаю объект класса главной страницы
        HomePage objHomePage = new HomePage(driver);
        //выбираю на какую кнопку "Заказать" давить - на верхнюю up или в центре страницы middle
        objHomePage.chooseOrderButtonAndClick("middle");
        //создаю объект страницы OrderPage
        OrderPage objOrderPage = new OrderPage(driver);
        //заполняю поля формы
        objOrderPage.fillFieldsInFormOrderForWhoScooterIncorrect("Al","Af","M","ddd");
        //давлю на кнопку Далее
        objOrderPage.clickButtonFurther();
        //проверяю соответствие текста ошибки под полем Имя при некорректном вводе
        String actualMistakeNameField = objOrderPage.getTextFromNameFieldMistake();
        assertEquals("Текст ошибки поля Имя не соответствует требованиям","Введите корректное имя",actualMistakeNameField);
        }
    @Test
    public void isMistakeTextCorrectInFieldFamilyName(){
        //создаю объект класса главной страницы
        HomePage objHomePage = new HomePage(driver);
        //выбираю на какую кнопку "Заказать" давить - на верхнюю up или в центре страницы middle
        objHomePage.chooseOrderButtonAndClick("middle");
        //создаю объект страницы OrderPage
        OrderPage objOrderPage = new OrderPage(driver);
        //заполняю поля формы
        objOrderPage.fillFieldsInFormOrderForWhoScooterIncorrect("Al","Af","M","ddd");
        //давлю на кнопку Далее
        objOrderPage.clickButtonFurther();
        //проверяю соответствие текста ошибки под полем Фамилия при некорректном вводе
        String actualMistakeFamilyNameField = objOrderPage.getTextFromFamilyNameFieldMistake();
        assertEquals("Текст ошибки поля Фамилия не соответствует требованиям","Введите корректную фамилию",actualMistakeFamilyNameField);
        }
    @Test
    public void isMistakeTextCorrectInFieldAddress(){
        //создаю объект класса главной страницы
        HomePage objHomePage = new HomePage(driver);
        //выбираю на какую кнопку "Заказать" давить - на верхнюю up или в центре страницы middle
        objHomePage.chooseOrderButtonAndClick("middle");
        //создаю объект страницы OrderPage
        OrderPage objOrderPage = new OrderPage(driver);
        //заполняю поля формы
        objOrderPage.fillFieldsInFormOrderForWhoScooterIncorrect("Al","Af","M","ddd");
        //давлю на кнопку Далее
        objOrderPage.clickButtonFurther();
        //проверяю соответствие текста ошибки под полем Адрес при некорректном вводе
        String actualMistakeAddressField = objOrderPage.getTextFromAddressFieldMistake();
        assertEquals("Текст ошибки поля Адрес не соответствует требованиям","Введите корректный адрес",actualMistakeAddressField);
        }
    @Test
    public void isMistakeTextCorrectInFieldSubwayStation(){
        //создаю объект класса главной страницы
        HomePage objHomePage = new HomePage(driver);
        //выбираю на какую кнопку "Заказать" давить - на верхнюю up или в центре страницы middle
        objHomePage.chooseOrderButtonAndClick("middle");
        //создаю объект страницы OrderPage
        OrderPage objOrderPage = new OrderPage(driver);
        //заполняю поля формы
        objOrderPage.fillFieldsInFormOrderForWhoScooterIncorrect("Al","Af","M","ddd");
        //давлю на кнопку Далее
        objOrderPage.clickButtonFurther();
        //проверяю соответствие текста ошибки под полем Станция Метро при некорректном вводе
        String actualMistakeSubwayStationField = objOrderPage.getTextFromSubwayStationFieldMistake();
        assertEquals("Текст ошибки поля Станция Метро не соответствует требованиям","Выберите станцию",actualMistakeSubwayStationField);
        }
    @Test
    public void isMistakeTextCorrectInFieldPhone(){
        //создаю объект класса главной страницы
        HomePage objHomePage = new HomePage(driver);
        //выбираю на какую кнопку "Заказать" давить - на верхнюю up или в центре страницы middle
        objHomePage.chooseOrderButtonAndClick("middle");
        //создаю объект страницы OrderPage
        OrderPage objOrderPage = new OrderPage(driver);
        //заполняю поля формы
        objOrderPage.fillFieldsInFormOrderForWhoScooterIncorrect("Al","Af","M","ddd");
        //давлю на кнопку Далее
        objOrderPage.clickButtonFurther();
        //проверяю соответствие текста ошибки под полем Телефон при некорректном вводе
        String actualMistakePhoneField = objOrderPage.getTextFromPhoneFieldMistake();
        assertEquals("Текст ошибки поля Телефон не соответствует требованиям","Введите корректный номер",actualMistakePhoneField);
    }
}