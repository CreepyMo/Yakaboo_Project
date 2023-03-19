package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProductListingPage {
    private SelenideElement firstFilterCheckBox = $(By.xpath("//span[text()='Хіти продажу']/preceding-sibling::input"));
    private SelenideElement secondFilterCheckBox = $(By.xpath("//span[text()='Товари в наявності']/preceding-sibling::input"));
    private SelenideElement thirdFilterCheckBox = $(By.xpath("//span[text()='Стівен Кінг']/preceding-sibling::input"));
    private SelenideElement sortingDropdown = $(By.xpath("//button[@class='sorting__btn']"));
    private SelenideElement sortingByPriceDescOption = $(By.xpath("//div[@class='sort-list']/button[contains(text(), 'Від найдорожчих')]"));

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

}
