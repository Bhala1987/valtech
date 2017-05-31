package com.valtech;

import com.valtech.pages.HomePage;
import com.valtech.utility.Table;
import com.valtech.utility.TestConfig;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext
public class PageRepository
{
    protected @Autowired
    HomePage homePage;

    protected @Autowired WebDriver driver;

    protected @Autowired Table tableObj;

}
