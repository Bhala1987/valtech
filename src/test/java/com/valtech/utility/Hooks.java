package com.valtech.utility;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.concurrent.TimeUnit;

@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext
public class Hooks {

    @Autowired
    WebDriver driver;


    @Before
    public void Setup() throws Throwable {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @After
    public void embedScreenshot(Scenario scenario) {

        driver.quit();
    }


}
