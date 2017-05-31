package com.valtech.stepDefs;

import com.valtech.PageRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;

;

/**
 * Created by ushabhala on 30/05/2017.
 */
public class valtech  extends PageRepository {

    @Given("^I am on valtech homepage$")
    public void iAmOnValtechHomepage() throws Throwable {
        String title = homePage.openPageValtech();
        assertThat("This is not a Valtech homepage", title.contains("Valtech"));
    }

    @Then("^I check for \"([^\"]*)\" section$")
    public void iCheckForSection(String section) throws Throwable {
        assertThat("There is no latest news section", section.equals(homePage.latestNews()));
    }

    @When("^I click on the \"([^\"]*)\"$")
    public void iClickOnLink(String link) throws Throwable {
        //homePage.link.click();

        switch (link) {
            case "ABOUT":
                homePage.clickAbout();
                break;
            case "WORK":
                homePage.clickWork();
                break;
            case "SERVICES":
                homePage.clickServices();
                break;
        }


    }

    @Then("^I should see the \"([^\"]*)\" in \"([^\"]*)\"$")
    public void iShouldSeeTheInH(String page, String heading) throws Throwable {
        String pageheading = homePage.pageHeading();
        assertThat("The page heading is incorrect", pageheading.equals(page));
        String tagname = homePage.pageTag();
        assertThat("The tag is incorrect", tagname.equals(heading));
    }

    @Then("^check how many offices$")
    public void checkHowManyOffices() throws Throwable {
        String offices1 = homePage.contacts();
        String[] offices = offices1.split("\\|");
        int numberOfOffices = offices.length;
        System.out.println("The number of offices in valtech is "+numberOfOffices);
    }
}