package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageBurgers {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPageBurgers(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // локатор Email
    private By emailInput = By.cssSelector("input[name='name']");

    // локатор пароль
    private By passwordInput = By.cssSelector("input[type='password']");

    // локатор войти
    private final By loginButton = By.xpath("//button[text()='Войти']");

    // локатор зарегистрироваться
    private By registerLink = By.cssSelector("a[href='/register']");

    // Восстановить пароль
    private By restorePasswordButton =
            By.cssSelector("a[href*='forgot-password']");

    // кнопка оформить заказ
    private By orderButton = By.xpath("//button[.='Оформить заказ']");

    // Метод клик, ввод значения в поле Email
    @Step("Ввести email: {email}")
    public void enterEmail(String email) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажать кнопку «Войти»") // добавила явное ожидание, потому что без ожидания тест падал
    public void clickLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(loginButton))
                .click();
    }

    @Step("Нажать ссылку «Зарегистрироваться»")
    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    @Step("Нажать кнопку «Восстановить пароль»")
    public void clickRestorePasswordButton() {
        driver.findElement(restorePasswordButton).click();
    }

    @Step("Дождаться загрузки главной страницы")
    public void waitForMainPageToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton));
    }

    @Step("Дождаться загрузки страницы авторизации")
    public void waitForLoginPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(emailInput));
    }
}