
package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {

    private WebDriver driver;

    private By pageTitle = By.className("title");

    // Constructor
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Get page title
    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }
}
