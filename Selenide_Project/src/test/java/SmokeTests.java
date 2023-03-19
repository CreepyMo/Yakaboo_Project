import com.codeborne.selenide.*;
import org.example.MainPage;
import org.example.ProductListingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

@Test
public class SmokeTests {

    @BeforeMethod
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10;
        open("https://www.yakaboo.ua/");
    }

    @Test
    public void registerWithValidDataTest() throws InterruptedException, TimeoutException {
        MainPage mainPage = new MainPage();
        mainPage.registerNewUser();
        SelenideElement successMessage = $(By.xpath("//div[contains(text(), 'Аккаунт успішно зареєстровано!')]"));
        SelenideElement profileDiv = $(By.xpath("//div[@class='ui-btn-profile btn-profile']"));
        successMessage.should(appear, Duration.ofSeconds(5));
        profileDiv.should(appear, Duration.ofSeconds(5));
        Assert.assertTrue(successMessage.isDisplayed());
        Assert.assertTrue(profileDiv.isDisplayed());
        Selenide.closeWebDriver();
    }

    @Test
    public void loginWithValidCredsTest() throws TimeoutException, InterruptedException {
        MainPage mainPage = new MainPage();
        mainPage.loginWithValidCreds();
        SelenideElement successMessage = $(By.xpath("//div[contains(text(), 'Ви ввійшли в обліковий запис!')]"));
        SelenideElement profileDiv = $(By.xpath("//div[@class='ui-btn-profile btn-profile']"));
        successMessage.should(appear, Duration.ofSeconds(5));
        profileDiv.should(appear, Duration.ofSeconds(5));
        Assert.assertTrue(successMessage.isDisplayed());
        Assert.assertTrue(profileDiv.isDisplayed());
        Selenide.closeWebDriver();
    }

    @Test
    public void addProductToCartTest() throws TimeoutException, InterruptedException {
        MainPage mainPage = new MainPage();
        mainPage.addProductToCart();
        SelenideElement successMessage = $(By.xpath("//div[contains(text(), 'Товар додано до кошика!')]"));
        successMessage.should(appear, Duration.ofSeconds(5));
        Assert.assertTrue(successMessage.isDisplayed());
        $(By.xpath("//button[@class='ui-btn-shopping-cart']")).click();
        $(By.xpath("//div[@class='checkout-product-card product-cart']")).should(appear, Duration.ofSeconds(5));
        int quantityAddedToCart = $$(By.xpath("//div[@class='checkout-product-card product-cart']")).size();
        Assert.assertEquals(quantityAddedToCart, 1);
        Selenide.closeWebDriver();
    }

    @Test
    public void searchByInvalidValueTest() throws TimeoutException, InterruptedException {
        $(By.xpath("//input[@id='search']")).setValue("rrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        SelenideElement noResultsMessage = $(By.xpath(
                "//p[contains(text(), 'За вашим запитом не знайдено жодних результатів')]"));
        noResultsMessage.should(appear, Duration.ofSeconds(3));
        Assert.assertTrue(noResultsMessage.isDisplayed());
        Selenide.closeWebDriver();
    }

    @Test
    public void applyFiltersTest() throws TimeoutException, InterruptedException {
        MainPage mainPage = new MainPage();
        ProductListingPage plp = mainPage.proceedToPLP();
        plp.applyFilters();

        List<SelenideElement> searchResultsList = $$(By.xpath("//div[@class='category-card view-category']"));
        for (SelenideElement e : searchResultsList) {
            String productValues = e.getAttribute("outerText");
            assert productValues != null;
            Assert.assertTrue(productValues.contains("Хіт") && productValues.contains("В наявності")
                    && productValues.contains("Стівен Кінг"));
        }
        Selenide.closeWebDriver();
    }

    @Test
    public void applySortingByPriceDescTest() throws TimeoutException, InterruptedException {
        MainPage mainPage = new MainPage();
        ProductListingPage plp = mainPage.proceedToPLP();
        plp.applySortingByPriceDesc();
        Thread.sleep(1000);

        List<SelenideElement> priceAttributes = $$(By.xpath(
                "//div[@class='ui-price-display category-card__price']//span[not(@class)]"));

       List<Integer> priceList = new ArrayList<>();

       for (SelenideElement e : priceAttributes) {
           String priceStr = e.getText();
           int priceInt = Integer.parseInt(priceStr);
           priceList.add(priceInt);
       }

        for (int i = 1; i < priceList.size(); i++) {
            Assert.assertTrue(priceList.get(i - 1) >= priceList.get(i));
        }
        Selenide.closeWebDriver();
    }
}
