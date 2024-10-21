package com.whaooFitness.project.pages;

import com.whaooFitness.project.base.BasePage;
import com.whaooFitness.project.locators.ElementLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Properties;

public class CartPage extends BasePage {

    public static WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        loadProperties();
    }

    public void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //"View and Edit Cart"
    public CartPage viewAndEditCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        //WebElement viewAndEditCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='View and Edit Cart']")));
        WebElement viewAndEditCartButton = wait.until(ExpectedConditions.elementToBeClickable(ElementLocator.VIEW_AND_EDIT_BTN.getLocator()));

        viewAndEditCartButton.click();
        System.out.println("Clic en 'View and Edit Cart'.");

        wait.until(ExpectedConditions.urlToBe("https://eu.wahoofitness.com/checkout/cart/"));
        System.out.println("Se ha cargado la página del carrito.");

        return this;
    }

    //"Proceed to Checkout"
    public void proceedToCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-role='proceed-to-checkout']")));
        WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(ElementLocator.PROCEED_TO_CHECKOUT.getLocator()));

        proceedToCheckoutButton.click();
        System.out.println("Hiciste clic en 'Proceed to Checkout'.");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://eu.wahoofitness.com/checkout/"));
        System.out.println("Has sido redirigido a la página de pago.");
    }

    // Método para intentar el pago y manejar el modal de error
    public void payNowAndHandleError() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //WebElement payNowButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'btn-blue') and @title='Pay Now' and normalize-space()='Pay Now']")));
        WebElement payNowButton = wait.until(ExpectedConditions.elementToBeClickable(ElementLocator.PAY_NOW_BTN.getLocator()));
        payNowButton.click();
        System.out.println("Intentaste realizar el pago.");

        //WebElement closeModalButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='OK'])[1]")));
        WebElement closeModalButton = wait.until(ExpectedConditions.elementToBeClickable(ElementLocator.OK_BTN_MODAL.getLocator()));
        closeModalButton.click();
        System.out.println("Hiciste clic en 'OK' para cerrar el modal de error.");

        /*wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//button[normalize-space()='OK'])[1]")));
        System.out.println("El modal de error ha desaparecido.");*/
    }
}
