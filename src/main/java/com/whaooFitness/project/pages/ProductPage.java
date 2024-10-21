package com.whaooFitness.project.pages;

import com.whaooFitness.project.base.BasePage;
import com.whaooFitness.project.locators.ElementLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class ProductPage extends BasePage {
    public static WebDriver driver;
    public ProductPage(WebDriver driver) {
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

    public static ProductPage verifyUserCorrectURL(){
        String currentUrl = driver.getCurrentUrl();

        String expectedUrl = properties.getProperty("base_url");
        if (currentUrl.equals(expectedUrl)) {
            System.out.println("Aviso: El usuario accedió correctamente a la URL: " + currentUrl);
        } else {
            System.out.println("Error: La URL actual es incorrecta. Se esperaba: " + expectedUrl + ", pero se obtuvo: " + currentUrl);
        }
        return null;
    }
    public void selectRandomProductAndAddToCart() {
        clickShopButton()
                .verifyAllProductsPage()
                .selectRandomAvailableProduct();

    }

    public ProductPage clickShopButton() {
        if (isElementPresent(ElementLocator.SHOP_BUTTON.getLocator())) {
            driver.findElement(ElementLocator.SHOP_BUTTON.getLocator()).click();
            System.out.println("Hiciste clic en el botón 'Shop' correctamente.");
        } else {
            throw new RuntimeException("Botón 'Shop' no encontrado.");
        }
        return this;
    }

    public ProductPage verifyAllProductsPage() {

        if (isElementPresent(ElementLocator.ALL_PRODUCTS_HEADER.getLocator())) {
            System.out.println("La página de 'All Products' se ha cargado correctamente.");
        } else {
            throw new RuntimeException("Página 'All Products' no encontrada.");
        }
        return this;
    }

    public ProductPage selectRandomAvailableProduct() {

        List<WebElement> productsAll = driver.findElements(ElementLocator.ALL_PRODUCTS_GRILL.getLocator());
        //WebElement allProductsSubtitle = driver.findElement(By.xpath("//h2[contains(@class, 'font-rbno2') and contains(text(), 'All Products')]"));
        WebElement allProductsSubtitle = driver.findElement(ElementLocator.ALL_PRODUCTS_SUBTITLE.getLocator());
        allProductsSubtitle.click();

        //WebElement productGrid = driver.findElement(By.cssSelector("#section-4 > div"));
        WebElement productGrid = driver.findElement(ElementLocator.PRODUCTS_GRILL.getLocator());
        //List<WebElement> products = productGrid.findElements(By.xpath(".//div[contains(@class, 'product-item')]"));
        List<WebElement> products = productGrid.findElements(ElementLocator.PRODUCT_GRILL.getLocator());
        List<WebElement> availableProducts = new ArrayList<>();

        // Filtrar productos que no están "Out of Stock" o "Coming Soon"
        for (WebElement product : products) {
            String availabilityText = product.getText().trim();

            if (!availabilityText.contains("Out of Stock") && !availabilityText.contains("Coming Soon")) {
                availableProducts.add(product);
            }
        }
        if (availableProducts.isEmpty()) {
            throw new RuntimeException("No hay productos disponibles.");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(availableProducts.size());
        WebElement selectedProduct = availableProducts.get(randomIndex);

        Actions actions = new Actions(driver);
        actions.moveToElement(selectedProduct).perform();
        System.out.println("Cursor colocado sobre el producto: " + selectedProduct.getText());

        //WebElement addToCartButton = selectedProduct.findElement(By.xpath(".//button[contains(text(), 'Add to cart')]"));
        WebElement addToCartButton = selectedProduct.findElement(ElementLocator.ADD_TO_CART_BTN.getLocator());
        if (addToCartButton.isDisplayed()) {
            System.out.println("El botón 'Add to cart' es visible.");
            addToCartButton.click();
            System.out.println("Producto añadido al carrito.");
        } else {
            throw new RuntimeException("El botón 'Add to cart' no es visible.");
        }

        return this;
    }

    private boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

}

