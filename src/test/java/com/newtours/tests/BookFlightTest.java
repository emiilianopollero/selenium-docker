package com.newtours.tests;

import com.newtours.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BookFlightTest {

    private WebDriver driver;
    private String noOfPassengers;
    private String expectedPrice;

    @BeforeTest
    @Parameters({"noOfPassengers", "expectedPrice"})
    public void setupDriver(String noOfPassengers, String expectedPrice){
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
        System.setProperty("webdriver.chrome.driver", "/home/emiliano/Downloads/chromedriver_linux64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        this.driver = new ChromeDriver(options);

    }

    @Test
    public void registrationPageTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo();
        registrationPage.enterUserDetails("selenium", "docker");
        registrationPage.enterUserCredentials("selenium", "docker");
        registrationPage.submit();
    }
    @Test(dependsOnMethods = "registrationPageTest")
    public void registrationConfirmationPageTest(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        registrationConfirmationPage.goToFlightsDetailsPage();
    }

    @Test(dependsOnMethods = "registrationConfirmationPageTest")
    public void flightDetailsPageTest(){
        FlightDetailsPage flightDetailsPage = new FlightDetailsPage(driver);
        flightDetailsPage.selectPassengers(noOfPassengers);
        flightDetailsPage.goToFindFlightsPage();
    }

    @Test(dependsOnMethods = "flightDetailsPageTest")
    public void findFlightPageTest(){
        FindFlightsPage findFlightsPage = new FindFlightsPage(driver);
        findFlightsPage.submitFindFlightPage();
        findFlightsPage.goToFlightConfirmationPage();
    }

    @Test(dependsOnMethods = "findFlightPageTest")
    public void flightConfirmationPageTest(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        String actualPrice = flightConfirmationPage.getPrice();
        Assert.assertEquals(actualPrice, expectedPrice);
    }
    @AfterTest
    public void quitBrowser(){
        this.driver.quit();
    }
}
