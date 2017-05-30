package com.valtech.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseActions {

    public static final By LATEST_NEWS = By.xpath("//h2[text()='Latest news']");
    @FindBy(xpath = "//h2[text()='Latest news']")
    public WebElement latest_news;
    @FindBy(css = "[data-icon='hamburger2']")
    public WebElement link;
    @FindBy(css = "[href=\"/about/\"]")
    public WebElement about;
    @FindBy(css = "[href=\"/cases/\"]")
    public WebElement work;
    @FindBy(css = "[href=\"/services/\"]")
    public WebElement services;
    @FindBy(css = "h1")
    public WebElement pageHeading;
    @FindBy(css = ".foot__offices")
    public WebElement offices;


    public String openPage() {

        action.goTo(getBaseUrl());
        driver.manage().window().maximize();
        return get.pageTitle();
    }


}
