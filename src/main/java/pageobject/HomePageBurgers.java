package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePageBurgers {
    private WebDriver driver;

    public HomePageBurgers(WebDriver driver) {
        this.driver = driver;
    }

    // Личный кабинет
    private By personalAccountButton = By.cssSelector("a[href='/account']");

    // Войти
    private By loginButton = By.xpath("//button[.='Войти в аккаунт']");

    // Конструктор булки
    By bunsTab = By.xpath("//span[text()='Булки']/parent::div");

    // Заголовок раздела Булки
    private By bunsSectionTitle = By.xpath("//h2[text()='Булки']");

    // Конструктор соусы
    private By saucesTab = By.xpath("//span[text()='Соусы']/parent::div");

    // Заголовок раздела Соусы
    By getSaucesTitle = By.xpath("//h2[text()='Соусы']");

    // Конструктор Начинки
    By fillingsTab = By.xpath("//span[text()='Начинки']/parent::div");

    // Заголовок раздела Начинки
    private By fillingsSectionTitle = By.xpath("//h2[text()='Начинки']");

    private By constructorHeader = By.xpath("//*[contains(text(),'Соберите бургер')]");

    @Step("Нажать кнопку «Войти в аккаунт»")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажать кнопку «Личный кабинет»")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Дождаться активации вкладки Булки")
    public void waitForBunsTabActive() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.attributeContains(bunsTab, "class", "tab_tab_type_current__2BEPc"));
    }

    @Step("Дождаться активации вкладки Соусы")
    public void waitForSaucesTabActive() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.attributeContains(saucesTab, "class", "tab_tab_type_current__2BEPc"));
    }

    @Step("Дождаться активации вкладки Начинки")
    public void waitForFillingsTabActive() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.attributeContains(fillingsTab, "class", "tab_tab_type_current__2BEPc"));
    }

    @Step("Проверить, что активна вкладка Булки")
    public boolean isBunsTabActive() {
        WebElement tab = driver.findElement(bunsTab);
        return tab.getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }

    @Step("Проверить, что активна вкладка Соусы")
    public boolean isSaucesTabActive() {
        WebElement tab = driver.findElement(saucesTab);
        return tab.getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }

    @Step("Проверить, что активна вкладка Начинки")
    public boolean isFillingsTabActive() {
        WebElement tab = driver.findElement(fillingsTab);
        return tab.getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }

    @Step("Перейти в раздел «Булки»")
    public void clickBunsTab() {
        WebElement tab = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(bunsTab));
        tab.click();
    }

   /* @Step("Проверить, что отображается раздел «Булки»")
    public boolean isBunsSectionDisplayed() {
        return driver.findElement(bunsSectionTitle).isDisplayed();
    }*/

    @Step("Перейти в раздел «Соусы»")
    public void clickSaucesTab() {
        WebElement tab = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(saucesTab));
        tab.click();
    }

    /*@Step("Проверить, что отображается раздел «Соусы»")
    public boolean isSaucesSectionDisplayed() {
        return driver.findElement(getSaucesTitle).isDisplayed();
    }*/

    @Step("Перейти в раздел «Начинки»")
    public void clickFillingsTab() {
        WebElement tab = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(fillingsTab));
        tab.click();
    }

    /*@Step("Проверить, что отображается раздел «Начинки»")
    public boolean isFillingsSectionDisplayed() {
        return driver.findElement(fillingsSectionTitle).isDisplayed();
    }*/

    @Step("Дождаться загрузки главной страницы")
    public void waitForMainPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(constructorHeader));
    }
}
