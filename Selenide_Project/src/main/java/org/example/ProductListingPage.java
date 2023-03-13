package org.example;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class ProductListingPage {
    private SelenideElement firstFilterCheckBox = $(By.xpath("//span[text()='Хіти продажу']/preceding-sibling::input"));
    private SelenideElement secondFilterCheckBox = $(By.xpath("//span[text()='Товари в наявності']/preceding-sibling::input"));
    private SelenideElement thirdFilterCheckBox = $(By.xpath("//span[text()='Стівен Кінг']/preceding-sibling::input"));
    private SelenideElement sortingDropdown = $(By.xpath("//button[@class='sorting__btn']"));
    private SelenideElement sortingByPriceDescOption = $(By.xpath("//div[@class='sort-list']/button[contains(text(), 'Від найдорожчих')]"));

    public ProductListingPage applyFilters() throws InterruptedException {
        actions().click(firstFilterCheckBox).build().perform();
        Thread.sleep(2000);
        actions().click(secondFilterCheckBox).build().perform();
        Thread.sleep(2000);
        actions().click(thirdFilterCheckBox).build().perform();
        Thread.sleep(2000);
        return page(ProductListingPage.class);
    }

    public ProductListingPage applySortingByPriceDesc() throws InterruptedException {
        applyFilters();
        sortingDropdown.click();
        sortingByPriceDescOption.click();
        return page(ProductListingPage.class);
    }

}
