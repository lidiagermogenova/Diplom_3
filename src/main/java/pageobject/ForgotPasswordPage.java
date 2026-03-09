package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    //Поле Email
    private By emailInput = By.cssSelector("input[name='name']");

    //Кнопка Восстановить
    private By recoverButton = By.cssSelector("form button");

    //Кнопка Войти
    private By loginLink =
            By.cssSelector("a[href='/login']");

    @Step("Нажать ссылку «Войти»")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    @Step("Ввести email: {email}")
    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Нажать кнопку «Восстановить»")
    public void clickRecoverButton() {
        driver.findElement(recoverButton).click();
    }
}
