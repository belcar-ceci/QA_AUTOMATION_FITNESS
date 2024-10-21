package com.whaoofitness.project.casestest;

import com.whaooFitness.project.pages.CartPage;
import com.whaooFitness.project.pages.CheckoutPage;
import com.whaooFitness.project.pages.ProductPage;
import com.whaoofitness.project.base.BaseTest;

import org.junit.Test;



public class WahooFitnessTest extends BaseTest {

    @Test
    public void testWahooFitnessCases() {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        productPage.loadUrl();
        productPage.verifyUserCorrectURL();
        productPage.selectRandomProductAndAddToCart();
        cartPage.viewAndEditCart();
        cartPage.proceedToCheckout();
        cartPage.payNowAndHandleError();
        checkoutPage.fillPaymentDetails();
        checkoutPage.clickPayNow();

    }

}
