package ru.yandex.scooter.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TrackPage {
    WebDriver driver;
    public TrackPage(WebDriver driver) {
        this.driver = driver;
    }
    //локатор для иллюстрации Такого заказа нет
    private final By notFoundElement = By.xpath(".//div[@class='Track_NotFound__6oaoY']/img");

    //метод проверки, что элемент "Такого заказа нет" отображается на странице
    public boolean isNotFoundImageVisible(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(notFoundElement));
        return driver.findElement(notFoundElement).isDisplayed();
    }
}
