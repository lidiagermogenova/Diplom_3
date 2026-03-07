import io.qameta.allure.Description;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import static org.junit.Assert.assertTrue;


public class ConstructorNavigationTest extends BaseTest {

    @Test
    @DisplayName("Конструктор: переход на вкладку «Булки»")
    @Description("Пользователь переключается на вкладку «Булки» в конструкторе бургера. Проверяется отображение секции булок.")
    public void shouldSwitchToBunsSection() {
        homePage.clickSaucesTab();     // уйти с Булок
        homePage.clickBunsTab();
        assertTrue(homePage.isBunsSectionDisplayed());
    }

    @Test
    @DisplayName("Конструктор: переход на вкладку «Соусы»")
    @Description("Пользователь переключается на вкладку «Соусы» в конструкторе бургера. Проверяется отображение секции соусов.")
    public void shouldSwitchToSaucesSection() {
        homePage.clickSaucesTab();    // перейти на Соусы
        assertTrue(homePage.isSaucesSectionDisplayed());
    }

    @Test
    @DisplayName("Конструктор: переход на вкладку «Начинки»")
    @Description("Пользователь переключается на вкладку «Начинки» в конструкторе бургера. Проверяется отображение секции начинок.")
    public void shouldSwitchToFillingsSection() {
        homePage.clickFillingsTab();
        assertTrue(homePage.isFillingsSectionDisplayed());
    }
}