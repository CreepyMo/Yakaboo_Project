package selenide.data;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class MainPage {
    private SelenideElement loginButton = $(By.xpath("//button[@class='ui-btn-account']"));
    private SelenideElement signUpButton = $(By.xpath("//button[@class='ui-btn-link']"));
    private SelenideElement firstNameField = $(By.xpath("//input[@name='reg_firstname']"));
    private SelenideElement lastNameField = $(By.xpath("//input[@name='reg_lastname']"));
    private SelenideElement telNumberField = $(By.xpath("//input[@type='tel']"));
    private SelenideElement signUpEmailField = $(By.xpath("//input[@name='reg_email']"));
    private SelenideElement signUpPasswordField = $(By.xpath("//input[@name='reg_password']"));
    private SelenideElement agreeWithTermsCheckbox = $(By.xpath("//input[@type='checkbox']"));
    private SelenideElement submitRegistrationButton = $(By.xpath("//button[@id='reg-submit']"));
    private SelenideElement signInLoginField = $(By.xpath("//input[@name='auth_login']"));
    private SelenideElement signInPasswordField = $(By.xpath("//input[@name='auth_password']"));
    private SelenideElement submitSignInButton = $(By.xpath("//button[@class='ui-btn-primary' and contains(text(), 'Увійти')]"));
    private SelenideElement productCard = $(By.xpath("(//div[@class='product-carousel__slide' and @tabindex='-1'])[7]"));
    private SelenideElement addtoCartButton = $(By.xpath("(//div[@class='product-carousel__slide' and @tabindex='-1'])[7]//button[contains(text(), 'До кошика')]"));
    private SelenideElement paperBooksButton = $(By.xpath("//span[contains(text(), 'Друковані книги')]"));
    private SelenideElement fictionBooksButton = $(By.xpath("//span[contains(text(), 'Художня література')]"));
    private SelenideElement registerSuccessMessage = $(By.xpath("//div[contains(text(), 'Аккаунт успішно зареєстровано!')]"));
    private SelenideElement profileDiv = $(By.xpath("//div[@class='ui-btn-profile btn-profile']"));
    private SelenideElement loginSuccessMessage = $(By.xpath("//div[contains(text(), 'Ви ввійшли в обліковий запис!')]"));
    private SelenideElement addToCartSuccessMessage = $(By.xpath("//div[contains(text(), 'Товар додано до кошика!')]"));
    private SelenideElement shoppingCartButton = $(By.xpath("//button[@class='ui-btn-shopping-cart']"));
    private SelenideElement firstProductInCart = $(By.xpath("//div[@class='checkout-product-card product-cart']"));
    private List<SelenideElement> productsInCart = $$(By.xpath("//div[@class='checkout-product-card product-cart']"));
    private SelenideElement searchField = $(By.xpath("//input[@id='search']"));
    private SelenideElement noResultsMessage =  $(By.xpath("//p[contains(text(), 'За вашим запитом не знайдено жодних результатів')]"));

    public MainPage clickLoginButton() {
        loginButton.click();
        switchTo().activeElement();
        return page(MainPage.class);
    }

    public MainPage clickSignUpButton() {
        clickLoginButton();
        signUpButton.click();
        switchTo().activeElement();
        return page(MainPage.class);
    }

    public MainPage loginWithValidCreds() {
        clickLoginButton();
        signInLoginField.setValue(Data.getEmailForLogin());
        signInPasswordField.setValue(Data.getPasswordForLogin());
        submitSignInButton.click();
        return page(MainPage.class);
    }

    public MainPage registerNewUser() {
        clickSignUpButton();
        firstNameField.should(appear, Duration.ofSeconds(5)).setValue(Data.getFirstNameForRegistration());
        lastNameField.setValue(Data.getLastNameForRegistration());
        telNumberField.setValue(RandomCredsGenerator.getRandomTelNumber());
        signUpEmailField.setValue(RandomCredsGenerator.getRandomEmail());
        signUpPasswordField.setValue(Data.getPasswordForRegistration());
        actions().moveToElement(agreeWithTermsCheckbox).click().build().perform();
        submitRegistrationButton.click();
        return page(MainPage.class);
    }

    public MainPage addProductToCart() {
        productCard.should(appear, Duration.ofSeconds(10));
        actions().scrollToElement(productCard).build().perform();
        actions().moveToElement(productCard).build().perform();
        addtoCartButton.should(appear, Duration.ofSeconds(5)).click();
        return page(MainPage.class);
    }

    public ProductListingPage proceedToPLP() {
        paperBooksButton.click();
        fictionBooksButton.should(appear, Duration.ofSeconds(5)).click();
        return Selenide.page(ProductListingPage.class);
    }

    public SelenideElement getRegisterSuccessMessage() {
        return this.registerSuccessMessage;
    }
    public SelenideElement getProfileDiv() {
        return this.profileDiv;
    }
    public SelenideElement getLoginSuccessMessage() {
        return this.loginSuccessMessage;
    }
    public SelenideElement getAddToCartSuccessMessage() {
        return this.addToCartSuccessMessage;
    }
    public SelenideElement getShoppingCartButton() {
        return this.shoppingCartButton;
    }
    public SelenideElement getFirstProductInCart() {
        return this.firstProductInCart;
    }
    public List<SelenideElement> getProductsInCart() {
        return this.productsInCart;
    }
    public SelenideElement getSearchField() {
        return this.searchField;
    }
    public SelenideElement getNoResultsMessage() {
        return this.noResultsMessage;
    }

}
