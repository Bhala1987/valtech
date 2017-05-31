package com.valtech.utility;

import com.valtech.driver.element.*;
import com.valtech.driver.javascript.Js;
import com.valtech.pages.HomePage;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan(basePackages = "com.valtech")
public class TestConfig {

    @Value("${driver}")
    private String driver;

    @Bean
    public WebDriver getWebDriver() {

        switch (driver) {
            case "chrome":
                ChromeDriverManager.getInstance().setup();
                return new ChromeDriver();
            case "firefox":
                FirefoxDriverManager.getInstance().setup();
                return new FirefoxDriver();
            case "ie":
                InternetExplorerDriverManager.getInstance().setup();
                return new InternetExplorerDriver();
            case "safari":
                return new SafariDriver();
            default:
                throw new IllegalArgumentException("Unknown driver " + driver);
        }
    }

    @Bean
    public Act getAct() {

        return new Act();
    }

    @Bean
    public Find getFind() {

        return new Find();
    }

    @Bean
    public Get getInstance() {

        return new Get();
    }

    @Bean
    public Is getTheIstanceOfIs() {

        return new Is();
    }

    @Bean
    public WaitFor getWaitFor() {

        return new WaitFor();
    }

    @Bean
    public Js getJs() {

        return new Js();
    }

    @Bean
    HomePage getAgentDesktop() {

        return new HomePage();
    }


    @Bean
    Table getTable() {

        return new Table();
    }

}
