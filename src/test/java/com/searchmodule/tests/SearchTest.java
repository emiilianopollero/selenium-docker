package com.searchmodule.tests;

import com.searchmodule.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SearchTest {

    private WebDriver driver;

    @BeforeTest
    public void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "/home/emiliano/Downloads/chromedriver_linux64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        this.driver = new ChromeDriver(options);
    }

    @Test
    @Parameters({"keyword"})
    public void search(String keyword) {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.goTo();
        searchPage.doSearch(keyword);
        searchPage.goToVideos();
        int size = searchPage.getResult();
        System.out.println(size);
        Assert.assertTrue(size > 0);
    }

    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }
}
