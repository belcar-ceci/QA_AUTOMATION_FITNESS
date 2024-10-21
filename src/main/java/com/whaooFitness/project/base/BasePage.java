package com.whaooFitness.project.base;

import com.whaooFitness.project.locators.ElementLocator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;



public class BasePage {

    protected WebDriver driver;

    protected static Properties properties;
    public BasePage(WebDriver driver){
        this.driver = driver;
        loadProperties();
    }

    private void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream fileInput = new FileInputStream("src/test/resources/config.properties");
            properties.load(fileInput);
            fileInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadUrl(){
        String baseUrl = properties.getProperty("base_url");
        driver.get(baseUrl);
        //driver.findElements(ElementLocator.ACCEPT_CK_BUTTON.getLocator()).click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.visibilityOfElementLocated(ElementLocator.ACCEPT_CK_BUTTON.getLocator()));
            acceptCookiesButton.click();
            System.out.println("El usuario ha cerrado las cookies");
        } catch (NoSuchElementException e) {
            System.out.println("Botón de cookies no encontrado. Continuando...");
        }
    }


}
