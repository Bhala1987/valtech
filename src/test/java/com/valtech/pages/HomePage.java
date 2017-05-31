package com.valtech.pages;

import org.openqa.selenium.By;

public class HomePage extends BaseActions {

    private static final By LATEST_NEWS = By.xpath("//h2[text()='Latest news']");
    public static final By ABOUT = By.cssSelector("[href=\"/about/\"]");
    public static final By WORK = By.cssSelector("[href=\"/cases/\"]");
    public static final By SERVICES = By.cssSelector("[href=\"/services/\"]");
    public static final By PAGEHEADING = By.cssSelector("h1");
    public static final By OFFICES = By.cssSelector(".foot__offices");


    public String openPageValtech() {

        action.goTo(getBaseUrl());
        driver.manage().window().maximize();
        return get.pageTitle();
    }

    public String latestNews() {
        return get.elementText(LATEST_NEWS)   ;
    }

    public void clickAbout(){
        action.clickElement(ABOUT);
    }

    public void clickWork(){
        action.clickElement(WORK);
    }

    public void clickServices(){
        action.clickElement(SERVICES);
    }

    public String pageHeading() {
        return get.elementText(PAGEHEADING);
    }

    public String pageTag(){
        return get.elementTag(PAGEHEADING);
    }

    public String contacts(){
        return get.elementText(OFFICES);
    }
}
