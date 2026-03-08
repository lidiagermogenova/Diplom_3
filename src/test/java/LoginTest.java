import api.UserApi;
import constants.Urls;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import testdata.UserTest;
import testdata.TestUserFactory;

import static org.junit.Assert.assertEquals;

public class LoginTest extends BaseTest {
    private UserTest user;
    private String accessToken;

    @Before
    public void createUser() {
        user = TestUserFactory.validUser();
        UserApi.createUser(user);
        accessToken = UserApi.loginAndGetToken(user);
    }

    @After
    public void deleteUser() {
        if (accessToken != null && !accessToken.isEmpty()) {
            UserApi.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Вход: по кнопке «Войти в аккаунт» на главной")
    @Description("Пользователь переходит на форму логина через кнопку «Войти в аккаунт», вводит корректные email и пароль и успешно авторизуется.")
    public void shouldLoginFromMainLoginButton() {
        homePage.clickLoginButton();
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickLoginButton();
        loginPage.waitForMainPageToLoad();
        assertEquals(Urls.BASE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Пользователь нажимает «Личный кабинет», вводит корректные данные и успешно входит в систему.")
    public void shouldLoginViaLoginButtonOnMainPage() {
        homePage.clickPersonalAccountButton();
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickLoginButton();
        loginPage.waitForMainPageToLoad();
        assertEquals(Urls.BASE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через кнопку «Зарегистрироваться» в форме регистрации")
    @Description("Пользователь открывает форму регистрации, переходит на страницу входа через ссылку «Войти» и успешно авторизуется.")
    public void shouldLoginViaLoginButtonOnRegistrationForm() {
        homePage.clickPersonalAccountButton();
        loginPage.clickRegisterLink();
        signUpPage.clickLoginLink();
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickLoginButton();
        loginPage.waitForMainPageToLoad();
        assertEquals(Urls.BASE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через кнопку «Восстановить пароль» в форме восстановления пароля")
    @Description("Пользователь открывает страницу восстановления пароля, переходит по ссылке «Войти» и успешно выполняет авторизацию.")
    public void shouldLoginViaLoginButtonOnForgotPasswordForm() {
        homePage.clickPersonalAccountButton();
        loginPage.clickRestorePasswordButton();
        forgotPassword.clickLoginLink();
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickLoginButton();
        loginPage.waitForMainPageToLoad();
        assertEquals(Urls.BASE_URL, driver.getCurrentUrl());
    }
}