package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPageBurgers {
    private WebDriver driver;

    public SignUpPageBurgers(WebDriver driver) {
        this.driver = driver;
    }

    // Поле Имя
    private By nameInput = By.cssSelector("input[name='name']");

    // Поле Email
    private By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");

    // Поле Пароль
    private By passwordInput = By.cssSelector("input[type='password']");

    // Кнопка Зарегистрироваться
    //By registerButton = By.cssSelector("form button");
    By registerButton = By.xpath("//button[text()='Зарегистрироваться']");

    // Кнопка Войти
    By loginLink = By.cssSelector("a[href='/login']");

    // Сообщение о некорректном пароле
    private By passwordError = By.xpath("//p[contains(@class, 'input__error')]");

    // Метод для ожидания кликабельности, чтобы каждый раз не писать этот код
    private WebElement waitForClickable(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
    // Метод для ожидания видимости, чтобы так же не писать каждый раз этот код
    private WebElement waitForVisible(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Ввести имя пользователя: {name}")
    public void enterName(String name) {
        WebElement element = waitForVisible(nameInput);
        element.clear();
        element.sendKeys(name);
    }

    @Step("Ввести email пользователя: {email}")
    public void enterEmail(String email) {
        WebElement element = waitForVisible(emailInput);
        element.clear();
        element.sendKeys(email);
    }

    @Step("Ввести пароль пользователя")
    public void enterPassword(String password) {
        WebElement element = waitForVisible(passwordInput);
        element.clear();
        element.sendKeys(password);
    }

    @Step("Заполнить все поля регистрации")
    public void fillAllFields(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
    }

    @Step("Нажать кнопку «Зарегистрироваться»")
    public void clickRegisterButton() {
        waitForClickable(registerButton).click();
    }

    @Step("Нажать ссылку «Войти»")
    public void clickLoginLink() {
        waitForClickable(loginLink).click();
    }

    @Step("Проверить, что отображается ошибка пароля")
    public boolean isPasswordErrorDisplayed() {
        return waitForVisible(passwordError).isDisplayed();
    }

    @Step("Дождаться загрузки страницы регистрации")
    public void waitForRegistrationPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("/register"));
    }

    @Step("Получить текст ошибки пароля")
    public String getPasswordErrorText() {
        return waitForVisible(passwordError).getText();
    }
}