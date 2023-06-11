package com.juaracoding;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestLogin {

    static WebDriver driver;
    static LoginPage loginPage;

    @BeforeClass
    public void setUp(){
        DriverSingleton.getInstance("chrome");
        driver = DriverSingleton.getDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        loginPage = new LoginPage();
    }

    @AfterClass
    public void closeBrowser(){
        delay(3);
        DriverSingleton.closeObjectInstance();
    }

    @Test(priority = 1)
    public void testValidLogin(){
        loginPage.login("Admin","admin123");
        delay(1);
        Assert.assertEquals(loginPage.getTxtDashboard(),"Dashboard");
        loginPage.logout();
    }

    @Test(priority = 2)
    public void testInvalidLogin(){
        loginPage.login("Admin","admin1234");
        delay(1);
        Assert.assertEquals(loginPage.getTxtInvalidCredentials(),"Invalid credentials");
    }

    static void delay(long detik){
        try {
            Thread.sleep(1000*detik);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
