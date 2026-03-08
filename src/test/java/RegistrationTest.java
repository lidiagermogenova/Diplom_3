import constants.Urls;
import constants.ValidationsMessage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import testdata.UserTest;
import testdata.TestUserFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Регистрация: успешная регистрация с валидными данными - переход на /login")
    @Description("Пользователь заполняет форму регистрации валидными данными, успешно создаёт аккаунт, проходит авторизацию и перенаправляется на главную страницу.")
    public void shouldRegisterSuccessfullyWithValidData() {
        UserTest user = TestUserFactory.validUser();

        // перейти на регистрацию
        homePage.clickLoginButton();
        loginPage.clickRegisterLink();

        // заполнить форму
        signUpPage.fillAllFields(user.getName(), user.getEmail(), user.getPassword());
        signUpPage.clickRegisterButton();

        // логин после регистрации
        loginPage.waitForLoginPageToLoad();
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickLoginButton();

        // Ждем главную страницу через HomePage
        homePage.waitForMainPageToLoad();
        assertTrue(driver.getCurrentUrl().startsWith(Urls.BASE_URL));
    }

    @Test
    @DisplayName("Регистрация: пароль меньше 6 символов - ошибка 'Некорректный пароль'")
    @Description("Пользователь вводит пароль длиной менее 6 символов. Система остаётся на странице регистрации и отображает сообщение об ошибке валидации пароля.")
    public void shouldShowErrorForPasswordLessThanSixCharacters() {
        UserTest user = TestUserFactory.userWithShortPassword();

        // перейти на регистрацию
        homePage.clickLoginButton();
        loginPage.clickRegisterLink();

        // заполнить форму
        signUpPage.fillAllFields(
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
        signUpPage.clickRegisterButton();
        signUpPage.waitForRegistrationPageToLoad(); // убрала ожидание из теста

        // остаемся и видим ошибку
        assertTrue(driver.getCurrentUrl().contains("/register"));
        assertTrue(signUpPage.isPasswordErrorDisplayed());
        assertEquals(ValidationsMessage.PASSWORD_INVALID,
                signUpPage.getPasswordErrorText()
        );
    }
}