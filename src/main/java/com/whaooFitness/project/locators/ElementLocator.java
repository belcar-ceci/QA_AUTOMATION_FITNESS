package com.whaooFitness.project.locators;

import org.openqa.selenium.By;

public enum ElementLocator {

    //ProductPage
    ACCEPT_CK_BUTTON(By.id("onetrust-accept-btn-handler")),
    SHOP_BUTTON(By.xpath("//a[contains(@class, 'cat menu-step01') and contains(span, 'Shop')]")),
    ALL_PRODUCTS_HEADER(By.xpath("//h1[contains(@class, 'headline') and contains(text(), 'All Products')]")),
    ALL_PRODUCTS_GRILL(By.xpath("//body")),
    ALL_PRODUCTS_SUBTITLE(By.xpath("//h2[contains(@class, 'font-rbno2') and contains(text(), 'All Products')]")),
    PRODUCTS_GRILL(By.cssSelector("#section-4 > div")),
    PRODUCT_GRILL(By.xpath(".//div[contains(@class, 'product-item')]")),
    ADD_TO_CART_BTN(By.xpath(".//button[contains(text(), 'Add to cart')]")),

    //CartPage
    VIEW_AND_EDIT_BTN(By.xpath("//span[normalize-space()='View and Edit Cart']")),
    PROCEED_TO_CHECKOUT(By.xpath("//button[@data-role='proceed-to-checkout']")),
    PAY_NOW_BTN(By.xpath("//button[contains(@class, 'btn-blue') and @title='Pay Now' and normalize-space()='Pay Now']")),
    OK_BTN_MODAL(By.xpath("(//button[normalize-space()='OK'])[1]")),

    //CheckoutPage
    CHECKOUT_HEADER(By.xpath("//*[@id='checkout']/div[1]/h1")),
    EMAIL_ADDRESS(By.cssSelector("#customer-email")),
    FIRST_NAME(By.xpath("//input[@name='firstname']")),
    LAST_NAME(By.xpath("//input[@name='lastname']")),
    STREET_ADDRESS(By.xpath("//input[@name='street[0]']")),
    CITY(By.xpath("//input[@name='city']")),
    POSTAL_CODE(By.xpath("//input[@name='postcode']")),
    PHONE_NUMBER(By.xpath("//input[@name='telephone']"));

    private By locator;
    ElementLocator(By locator) {
        this.locator = locator;
    }

    public By getLocator() {
        return locator;
    }



}
