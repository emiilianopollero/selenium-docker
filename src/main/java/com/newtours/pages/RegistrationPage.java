package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(name = "firstName")
    private WebElement firstName;
    @FindBy(name = "lastName")
    private WebElement lastName;
    @FindBy(name = "email")
    private WebElement username;
    @FindBy(name = "password")
    private WebElement password;
    @FindBy(name = "confirmPassword")
    private WebElement confirmPassword;
    @FindBy(name = "register")
    private WebElement submitBtn;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void goTo() {
        this.driver.get("https://vins-udemy.s3.amazonaws.com/docker/docker-book-flight.html");
        this.wait.until(ExpectedConditions.visibilityOf(this.firstName));
    }

    public void enterUserDetails(String firstName, String lastName) {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
    }

    public void enterUserCredentials(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.confirmPassword.sendKeys(password);
    }

    public void submit() {
        submitBtn.click();
    }
}
