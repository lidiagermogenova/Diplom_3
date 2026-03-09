import constants.Urls;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.ForgotPasswordPage;
import pageobject.HomePageBurgers;
import pageobject.LoginPageBurgers;
import pageobject.SignUpPageBurgers;

import java.io.File;

public class BaseTest {

    protected WebDriver driver;
    protected ForgotPasswordPage forgotPassword;
    protected HomePageBurgers homePage;
    protected LoginPageBurgers loginPage;
    protected SignUpPageBurgers signUpPage;

    @Before
    public void setUp() {
        driver = createDriver();

        forgotPassword = new ForgotPasswordPage(driver);
        homePage = new HomePageBurgers(driver);
        loginPage = new LoginPageBurgers(driver);
        signUpPage = new SignUpPageBurgers(driver);

        driver.get(Urls.BASE_URL);
    }

    private WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        if ("yandex".equals(browser)) {

            String yandexBinary = System.getProperty("yandex.binary");

            if (yandexBinary == null || yandexBinary.trim().isEmpty()) {
                String[] candidates = {
                        "/Applications/Yandex.app/Contents/MacOS/Yandex",
                        System.getProperty("user.home") + "/Applications/Yandex.app/Contents/MacOS/Yandex"
                };
                for (String c : candidates) {
                    if (new File(c).exists()) {
                        yandexBinary = c;
                        break;
                    }
                }
            }

            if (yandexBinary == null || yandexBinary.trim().isEmpty()) {
                throw new IllegalStateException(
                        "Не найден Yandex Browser. Укажи путь: -Dyandex.binary=\"/Applications/Yandex.app/Contents/MacOS/Yandex\""
                );
            }
            options.setBinary(yandexBinary);
        }
        return new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}