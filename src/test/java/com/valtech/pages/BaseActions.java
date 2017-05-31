package com.valtech.pages;

import com.valtech.driver.element.Act;
import com.valtech.driver.element.Get;
import com.valtech.driver.element.Is;
import com.valtech.driver.element.WaitFor;
import com.valtech.driver.javascript.Js;
import com.valtech.utility.Table;
import com.valtech.utility.TestConfig;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.Random;


@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext
public class BaseActions {

    public static String RANDOM_STR = "";

    @Autowired
    protected WebDriver driver;

    @Autowired
    protected Act action;

    @Autowired
    protected Get get;

    @Autowired
    protected Is is;

    @Autowired
    protected WaitFor waitFor;

    @Autowired
    protected Js js;

    @Autowired
    protected Table table;

    @Value("${env}")
    protected String env;

    @Value("${local}")
    private String local;

    @Value("${dev}")
    private String dev;

    @Value("${systest}")
    private String systest;

    String getBaseUrl() {

        String baseUrl = null;
        switch (env) {
            case "dev":
                baseUrl = local;
                return baseUrl;
            default:
                throw new IllegalArgumentException("Unknown environment '" + env + "' used to trigger the tests. Please use 'local' or 'dev' or 'systest'");
        }
    }

    protected String getRandomString(int stringLength) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < stringLength) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    protected int get8DigitRandomNumber() {
        Random rnd = new Random();
        return  10000000 + rnd.nextInt(90000000);
    }

    public String replaceScenarioVal(String value) {

        return value.replace("SCENARIO_VAL", RANDOM_STR);
    }

}
