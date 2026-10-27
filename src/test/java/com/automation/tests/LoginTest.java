
package com.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import com.automation.listeners.TestListener;
import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;
import com.automation.pages.ProductsPage;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {
    	
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage =
                new ProductsPage(driver);

        Assert.assertEquals(
                productsPage.getPageTitle(),
                "Products",
                "Login failed: Products page not displayed"
        );
    }

    @Test
    public void invalidLoginTest() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("wrong_user", "wrong_password");

        Assert.assertTrue(
                loginPage.getErrorMessage()
                         .contains("Username and password do not match"),
                "Expected login error was not displayed"
        );
    }
    @Test
    public void LoginTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);

        Assert.assertEquals(
            productsPage.getPageTitle(),
            "Products"
        );

        takeScreenshot("Valid_Login");
    }
}
