import api.UserApi;
import org.junit.After;
import org.junit.Before;
import testdata.UserTest;
import testdata.TestUserFactory;

public abstract class AuthorizedBaseTest extends BaseTest {

    protected UserTest user;
    private String accessToken;

    @Before
    public void setUpAuthorizedUser() {
        // создаём пользователя через API
        user = TestUserFactory.validUser();
        UserApi.createUser(user);

        // получаем токен (нужен для удаления)
        accessToken = UserApi.loginAndGetToken(user);

        // логинимся через UI
        homePage.clickLoginButton();
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickLoginButton();
    }

    @After
    public void cleanUpAuthorizedUser() {
        // 4️⃣ чистим пользователя после теста
        UserApi.deleteUser(accessToken);
    }
}