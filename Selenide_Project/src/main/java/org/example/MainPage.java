package org.example;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

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
        signInLoginField.setValue("testingemail@gmail.com");
        signInPasswordField.setValue("testPassword123");
        submitSignInButton.click();
        return page(MainPage.class);
    }

    public MainPage registerNewUser() throws InterruptedException {
        clickSignUpButton();
        firstNameField.should(appear, Duration.ofSeconds(5)).setValue("UserFirstName");
        lastNameField.setValue("UserLastName");
        telNumberField.setValue(RandomCredsGenerator.getRandomTelNumber());
        signUpEmailField.setValue(RandomCredsGenerator.getRandomEmail());
        signUpPasswordField.setValue("testPassword456");
        actions().moveToElement(agreeWithTermsCheckbox).click().build().perform();
        Thread.sleep(3000);
        submitRegistrationButton.click();
        return page(MainPage.class);
    }

    public MainPage addProductToCart() {
        productCard.should(appear, Duration.ofSeconds(5));
        actions().scrollToElement(productCard).build().perform();
        actions().moveToElement(productCard).build().perform();
        addtoCartButton.should(appear, Duration.ofSeconds(5)).click();
        return page(MainPage.class);
    }

    public ProductListingPage proceedtoPLP() throws InterruptedException {
        paperBooksButton.click();
        fictionBooksButton.should(appear, Duration.ofSeconds(5)).click();
        Thread.sleep(4000);
        return page(ProductListingPage.class);
    }

}
