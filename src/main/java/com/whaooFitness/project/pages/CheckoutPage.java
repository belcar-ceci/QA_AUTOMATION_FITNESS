package com.whaooFitness.project.pages;

import com.whaooFitness.project.base.BasePage;
import com.whaooFitness.project.locators.ElementLocator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class CheckoutPage extends BasePage {

    public static WebDriver driver;
    public CheckoutPage(WebDriver driver) {
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
    public void fillPaymentDetails() {
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://eu.wahoofitness.com/checkout/";

        if (!currentUrl.equals(expectedUrl)) {
            throw new RuntimeException("El usuario no está en la página de pago. URL actual: " + currentUrl);
        }
        String emailAddressValue = properties.getProperty("emailAddress");
        String firstNameValue = properties.getProperty("firstName");
        String lastNameValue = properties.getProperty("lastName");
        String streetAddressValue = properties.getProperty("streetAddress");
        String cityValue = properties.getProperty("city");
        String postalCodeValue = properties.getProperty("zip/PostalCode");
        String phoneNumberValue = properties.getProperty("phoneNumber");

        //Email
        //new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> true);
        //WebElement headerElement = driver.findElement(By.xpath("//*[@id='checkout']/div[1]/h1"));
        WebElement headerElement = driver.findElement(ElementLocator.CHECKOUT_HEADER.getLocator());
        headerElement.click();
        System.out.println("Hiciste clic en el encabezado para asegurarte de que el modal se haya cerrado.");

        // Esperar 5 segundos antes de continuar
        try {
            Thread.sleep(5000); // Espera 5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //WebElement emailAddressField = driver.findElement(By.cssSelector("#customer-email"));
        WebElement emailAddressField = driver.findElement(ElementLocator.EMAIL_ADDRESS.getLocator());
        emailAddressField.clear();
        emailAddressField.sendKeys(emailAddressValue);
        System.out.println("Email ingresado: " + emailAddressValue);

        // Llenar el campo de nombre
        //WebElement firstNameField = driver.findElement(By.xpath("//input[@name='firstname']"));
        WebElement firstNameField = driver.findElement(ElementLocator.FIRST_NAME.getLocator());
        firstNameField.click();
        firstNameField.sendKeys(firstNameValue);
        System.out.println("Nombre ingresado: " + firstNameValue);

        // Llenar el campo de apellido
        //WebElement lastNameField = driver.findElement(By.xpath("//input[@name='lastname']"));
        WebElement lastNameField = driver.findElement(ElementLocator.LAST_NAME.getLocator());
        lastNameField.click();
        lastNameField.sendKeys(lastNameValue);
        System.out.println("Apellido ingresado: " + lastNameValue);

        // Llenar el campo de dirección
        //WebElement streetAddressField = driver.findElement(By.xpath("//input[@name='street[0]']"));
        WebElement streetAddressField = driver.findElement(ElementLocator.STREET_ADDRESS.getLocator());
        streetAddressField.click();
        streetAddressField.sendKeys(streetAddressValue);
        System.out.println("Dirección ingresada: " + streetAddressValue);

        // Llenar el campo de ciudad
        //WebElement cityField = driver.findElement(By.xpath("//input[@name='city']"));
        WebElement cityField = driver.findElement(ElementLocator.CITY.getLocator());
        cityField.sendKeys(cityValue);
        System.out.println("Ciudad ingresada: " + cityValue);

        // Seleccionar el país
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='country_id']")));
            System.out.println("Campo de selección de país encontrado.");
        } catch (TimeoutException e) {
            System.out.println("El campo de selección de país no se encontró.");
            return;
        }
        WebElement countryField = driver.findElement(By.xpath("//select[@name='country_id']"));
        countryField.click();
        System.out.println("Desplegable de países abierto.");

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[@value='ES'][normalize-space()='Spain']")));
            System.out.println("Opción 'Spain' encontrada.");
        } catch (TimeoutException e) {
            System.out.println("Opción 'Spain' no se encontró.");
            return;
        }
        WebElement countryOption = driver.findElement(By.xpath("//option[@value='ES'][normalize-space()='Spain']"));
        countryOption.click();
        System.out.println("País seleccionado: Spain");

        WebElement bodyElement = driver.findElement(By.tagName("body"));
        bodyElement.click();
        System.out.println("Hiciste clic en el body para cerrar el desplegable.");

        // Llenar el campo de código postal
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='postcode']")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(ElementLocator.POSTAL_CODE.getLocator()));
            System.out.println("Campo de código postal encontrado.");
        } catch (TimeoutException e) {
            System.out.println("El campo de código postal no se encontró.");
            return;
        }

        // Localizar el campo del código postal
        WebElement postalCodeField = driver.findElement(By.xpath("//input[@name='postcode']"));

        // Hacer scroll hacia el campo del código postal usando JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", postalCodeField);

        // Esperar un momento para asegurarte de que el scroll se ha completado
        try {
            Thread.sleep(500); // Ajusta según sea necesario
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Hacer clic en el campo y enviar el código postal
        postalCodeField.clear();
        postalCodeField.click();
        postalCodeField.sendKeys(postalCodeValue);
        System.out.println("Código postal ingresado: " + postalCodeValue);


        // Llenar el campo de número de teléfono
        WebElement phoneNumberField = driver.findElement(ElementLocator.PHONE_NUMBER.getLocator());
        phoneNumberField.click();
        phoneNumberField.sendKeys(phoneNumberValue);
        System.out.println("Número de teléfono ingresado: " + phoneNumberValue);

        return;
    }


    public void clickPayNow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement payNowButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn-blue w-full' and normalize-space()='Pay Now']")));

        payNowButton.click();
        System.out.println("Hiciste clic en el botón 'Pay Now'.");
    }





}
