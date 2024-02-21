package ru.yandex.scooter.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


//Класс главной страницы сайта Самокат
public class HomePage {
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";
    private final WebDriver driver;
    // локатор для кнопки согласия с куками
    private final By cookiesAgreeButton = By.className("App_CookieButton__3cvqF");
    //Локаторы кнопок вопросов раздела Вопросы о важном от 0 до 7 - 8 вопросов
    private final By buttonsImportantQuestionsList_0 = By.id("accordion__heading-0");
    private final By buttonsImportantQuestionsList_1 = By.id("accordion__heading-1");
    private final By buttonsImportantQuestionsList_2 = By.id("accordion__heading-2");
    private final By buttonsImportantQuestionsList_3 = By.id("accordion__heading-3");
    private final By buttonsImportantQuestionsList_4 = By.id("accordion__heading-4");
    private final By buttonsImportantQuestionsList_5 = By.id("accordion__heading-5");
    private final By buttonsImportantQuestionsList_6 = By.id("accordion__heading-6");
    private final By buttonsImportantQuestionsList_7 = By.id("accordion__heading-7");
    //Массив из локаторов кнопок раздела Вопросы о важном
    private final By[] buttonsImportantQuestionsArray = {buttonsImportantQuestionsList_0, buttonsImportantQuestionsList_1, buttonsImportantQuestionsList_2, buttonsImportantQuestionsList_3, buttonsImportantQuestionsList_4, buttonsImportantQuestionsList_5, buttonsImportantQuestionsList_6, buttonsImportantQuestionsList_7};
    //локаторы к актуальным ответам раздела Вопросы о важном от 0 да 7 - 8 ответов
    private final By answersImportantQuestionsList_0 = By.xpath(".//div[@id='accordion__panel-0']/p");
    private final By answersImportantQuestionsList_1 = By.xpath(".//div[@id='accordion__panel-1']/p");
    private final By answersImportantQuestionsList_2 = By.xpath(".//div[@id='accordion__panel-2']/p");
    private final By answersImportantQuestionsList_3 = By.xpath(".//div[@id='accordion__panel-3']/p");
    private final By answersImportantQuestionsList_4 = By.xpath(".//div[@id='accordion__panel-4']/p");
    private final By answersImportantQuestionsList_5 = By.xpath(".//div[@id='accordion__panel-5']/p");
    private final By answersImportantQuestionsList_6 = By.xpath(".//div[@id='accordion__panel-6']/p");
    private final By answersImportantQuestionsList_7 = By.xpath(".//div[@id='accordion__panel-7']/p");
    //Массив локаторов из текстовых ответов раздела Вопросы о важном
    private final By[] answersImportantQuestionsArray = {answersImportantQuestionsList_0, answersImportantQuestionsList_1, answersImportantQuestionsList_2, answersImportantQuestionsList_3, answersImportantQuestionsList_4, answersImportantQuestionsList_5, answersImportantQuestionsList_6, answersImportantQuestionsList_7};

    //локатор для верхней кнопки Заказать
    private final By upOrderButton = By.xpath(".//button[@class='Button_Button__ra12g']");
    //локатор для нижней кнопки Заказать
    private final By middleOrderButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");
    //локатор для логотипа самоката
    private final By logoSamokat = By.xpath(".//a[@class='Header_LogoScooter__3lsAR']");
    //локатор для логотипа Яндекса
    private final By logoYandex = By.cssSelector("a.Header_LogoYandex__3TSOI");
    //локатор для кнопки Статус заказа
    private final By statusOrderButton = By.xpath(".//button[@class='Header_Link__1TAG7']");
    //локатор для поля ввода "Введите номер заказа"
    private final By enterNumberOrderField = By.xpath(".//input[@placeholder='Введите номер заказа']");
    //локатор для кнопки Go
    private final By goButton = By.xpath(".//button[@class='Button_Button__ra12g Header_Button__28dPO']");

    public HomePage(WebDriver driver) {

        this.driver = driver;
    }

    //открыть главную страницу Самоката
    public void openScooterPage() {
        driver.get(URL);
    }
    //метод клика по кнопке согласия с куками, если она появилась
    public void acceptCookies() {
        driver.findElement(cookiesAgreeButton).isDisplayed();
        driver.findElement(cookiesAgreeButton).click();
    }
    //пролистнуть страницу и кликнуть по кнопке в разделе Вопросы о важном + явное ожидание появления n-ого вопроса
    public void clickButtonsImportantQuestions(int listIndex) {
        By locator = buttonsImportantQuestionsArray[listIndex];
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
    }
    //метод для получения текста из соответствующего ответа раздела Вопросы о важном
    public String getAnswersImportantQuestions(int listIndex) {
        By locator = answersImportantQuestionsArray[listIndex];
        return driver.findElement(locator).getText();
    }

    //кликнуть по верхней кнопке "Заказать" на главной странице
    public void upOrderButtonClick() {

        driver.findElement(upOrderButton).click();

    }

    //прокрутка до нижней кнопки "Заказать" и кликнуть по нижней кнопке Заказать
    public HomePage middleOrderButtonClick() {
        WebElement element = driver.findElement(middleOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(middleOrderButton).click();
        return this;
    }

    //выбор кнопки "Заказать" на странице - верхняя или нижняя и клик по ней
    public HomePage chooseOrderButtonAndClick(String buttonLocation) {
        if (buttonLocation.equals("up")) {
            upOrderButtonClick();
            return this;
        } else if (buttonLocation.equals("middle")) {
            middleOrderButtonClick();
            return this;
        }
        return this;
    }

    //Метод клика по логотипу Самоката на главной
    public void samokatLogoClick() {
        driver.findElement(logoSamokat).click();
    }
    //Метод клика по логотипу Яндекса  на главной
    public void yandexLogoClick() {
        driver.findElement(logoYandex).click();
    }
    //Метод клика по кнопке Статус заказа на главной странице
    public void statusOrderButtonClick() {

        driver.findElement(statusOrderButton).click();

    }
    //Метод ввода значения в поле "Введите номер заказа"
    public void setEnterNumberOrderField(String numberOrder) {

        driver.findElement(enterNumberOrderField).sendKeys(numberOrder);

    }
    //Метод клика на кнопку GO
    public void goButtonClick() {

        driver.findElement(goButton).click();

    }
}