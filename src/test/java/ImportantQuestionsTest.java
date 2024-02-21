import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.scooter.pageObject.HomePage;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ImportantQuestionsTest  {
    WebDriver driver;
    private final int listIndex;
    private final String textAnswerExpected;

    //конструктор тест-класса Вопросы о важном с 2 параметрами
    public ImportantQuestionsTest(int listIndex, String textAnswerExpected) {
        this.listIndex = listIndex;
        this.textAnswerExpected = textAnswerExpected;
    }

    //набор тестовых данных
    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }
    //подключение драйвера браузера и установление неявного ожидания в 5 секунд
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    //закрываю браузер
    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void IsEqualsTextWhenClickedButton(){
        //создаю объект класса главной страницы
        HomePage objHomePage = new HomePage(driver);
        //открываю главную страницу
        objHomePage.openScooterPage();
        //если кнопка согласия кук появилась, давлю на нее
        objHomePage.acceptCookies();
        //скролл до кнопки с вопросом и клик по ней
        objHomePage.clickButtonsImportantQuestions(listIndex);
        //записываю в переменную актуальный ответ на вопрос
        String actualTextAnswer = objHomePage.getAnswersImportantQuestions(listIndex);
        //сравниваю результаты
        assertEquals("Текст ответа не совпадает. Вопрос: [" + listIndex + "].", textAnswerExpected, actualTextAnswer);
    }
}