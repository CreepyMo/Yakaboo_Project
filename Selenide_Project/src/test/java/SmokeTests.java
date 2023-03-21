import com.codeborne.selenide.*;
import org.example.MainPage;
import org.example.ProductListingPage;
import org.example.RandomCredsGenerator;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;

@Test
public class SmokeTests extends BaseTest {

    @Test
    public void registerWithValidDataTest() throws TimeoutException {
        MainPage mainPage = new MainPage();
        mainPage.registerNewUser();
        mainPage.getProfileDiv().should(appear, Duration.ofSeconds(5));
        mainPage.getRegisterSuccessMessage().should(appear, Duration.ofSeconds(5));
        Assert.assertTrue(mainPage.getProfileDiv().isDisplayed());
        Assert.assertTrue(mainPage.getRegisterSuccessMessage().isDisplayed());
    }

    @Test
    public void loginWithValidCredsTest() throws TimeoutException {
        MainPage mainPage = new MainPage();
        mainPage.loginWithValidCreds();
        mainPage.getLoginSuccessMessage().should(appear, Duration.ofSeconds(5));
        mainPage.getProfileDiv().should(appear, Duration.ofSeconds(5));
        Assert.assertTrue(mainPage.getLoginSuccessMessage().isDisplayed());
        Assert.assertTrue(mainPage.getProfileDiv().isDisplayed());
    }

    @Test
    public void addProductToCartTest() throws TimeoutException {
        MainPage mainPage = new MainPage();
        mainPage.addProductToCart();
        mainPage.getAddToCartSuccessMessage().should(appear, Duration.ofSeconds(5));
        Assert.assertTrue(mainPage.getAddToCartSuccessMessage().isDisplayed());
        mainPage.getShoppingCartButton().click();
        mainPage.getFirstProductInCart().should(appear, Duration.ofSeconds(5));
        int quantityAddedToCart = mainPage.getProductsInCart().size();
        Assert.assertEquals(quantityAddedToCart, 1);
    }

    @Test
    public void searchByInvalidValueTest() throws TimeoutException {
        MainPage mainPage = new MainPage();
        mainPage.getSearchField().setValue(RandomCredsGenerator.getRandomSearchRequest());
        mainPage.getNoResultsMessage().should(appear, Duration.ofSeconds(3));
        Assert.assertTrue(mainPage.getNoResultsMessage().isDisplayed());
    }

    @Test
    public void applyFiltersTest() throws TimeoutException {
        MainPage mainPage = new MainPage();
        ProductListingPage plp = mainPage.proceedToPLP();
        plp.applyFilters();
        plp.checkFiltersApply();
        Selenide.closeWebDriver();
    }

    @Test
    public void applySortingByPriceDescTest() throws TimeoutException, InterruptedException {
        MainPage mainPage = new MainPage();
        ProductListingPage plp = mainPage.proceedToPLP();
        plp.applySortingByPriceDesc();
        Thread.sleep(1000);

        List<Integer> priceList = new ArrayList<>();

        for (SelenideElement e : plp.getPriceAttributes()) {
            String priceStr = e.getText();
            int priceInt = Integer.parseInt(priceStr);
            priceList.add(priceInt);
        }

        for (int i = 1; i < priceList.size(); i++) {
            Assert.assertTrue(priceList.get(i - 1) >= priceList.get(i));
        }
    }
}
