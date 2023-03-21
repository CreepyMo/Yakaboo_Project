package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProductListingPage {
    private SelenideElement firstFilterCheckBox = $(By.xpath("//span[text()='Хіти продажу']/preceding-sibling::input"));
    private SelenideElement secondFilterCheckBox = $(By.xpath("//span[text()='Товари в наявності']/preceding-sibling::input"));
    private SelenideElement thirdFilterCheckBox = $(By.xpath("//span[text()='Стівен Кінг']/preceding-sibling::input"));
    private SelenideElement sortingDropdown = $(By.xpath("//button[@class='sorting__btn']"));
    private SelenideElement sortingByPriceDescOption = $(By.xpath("//div[@class='sort-list']/button[contains(text(), 'Від найдорожчих')]"));
    private List<SelenideElement> searchResultsList = $$(By.xpath("//div[@class='category-card view-category']"));
    private  List<SelenideElement> priceAttributes = $$(By.xpath("//div[@class='ui-price-display category-card__price']//span[not(@class)]"));


    public void applyFilters() {
        firstFilterCheckBox.should(visible, Duration.ofSeconds(10));
        actions().click(firstFilterCheckBox).build().perform();
        firstFilterCheckBox.should(Condition.selected, Duration.ofSeconds(10));
        actions().click(secondFilterCheckBox).build().perform();
        secondFilterCheckBox.should(Condition.selected, Duration.ofSeconds(10));
        actions().click(thirdFilterCheckBox).build().perform();
        thirdFilterCheckBox.should(Condition.selected, Duration.ofSeconds(10));
    }

    public void applySortingByPriceDesc() {
        applyFilters();
        sortingDropdown.click();
        sortingByPriceDescOption.click();
    }

    public List<SelenideElement> getPriceAttributes() {
        return this.priceAttributes;
    }

    public void checkFiltersApply() {
        for (SelenideElement e : searchResultsList) {
            String productValues = e.getAttribute("outerText");
            assert productValues != null;
            Assert.assertTrue(productValues.contains("Хіт") && productValues.contains("В наявності")
                    && productValues.contains("Стівен Кінг"));
        }
    }

}
