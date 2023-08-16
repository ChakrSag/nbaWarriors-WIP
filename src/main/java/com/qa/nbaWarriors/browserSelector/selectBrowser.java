package com.qa.nbaWarriors.browserSelector;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qa.nbaWarriors.BusinessComponants.commonComp;
import com.qa.nbaWarriors.SupportLibrary.reusableLibrary;
import com.qa.nbaWarriors.SupportLibrary.webDriverUtil;
import com.qa.nbaWarriors.objectRepository.commonPage;
import com.qa.nbaWarriors.readProperty.readPropertyFileData;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.qa.nbaWarriors.SupportLibrary.BrowserConfigurations;

public class selectBrowser {
    public static WebDriver driver;
    public static String scenarioName;
    protected Logger logEvent=Logger.getLogger(getClass().getSimpleName());
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static String suiteName;
    public static ExtentTest parentTest;
    public static ExtentTest childTest;
    public static File reportDirectoryCheck;
    protected static boolean nextsteptobeSkipped=false;
    protected static readPropertyFileData dataReadFromPropertyFile=new readPropertyFileData();

    protected static webDriverUtil wutil;

    public static commonPage commonpage;

    public static commonComp commoncomp;

    public WebDriver getBrowser(String browser) {
        try {
            if (System.getProperty("os.name").contains("Window")){
                if (browser.equalsIgnoreCase("Firefox")){
                    System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+BrowserConfigurations.fireFoxDriverLocation);
                    driver= new FirefoxDriver();
                    driver.manage().window().maximize();

                }
                else if (browser.equalsIgnoreCase("Chrome")){
                    System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+BrowserConfigurations.googleChromeDriverLocation);
                    ChromeOptions option = new ChromeOptions();
                    option.addArguments("disable-extensions");
                    option.addArguments("--start-maximized");
                    driver = new ChromeDriver(option);
                }

            }

            else if (System.getProperty("os.name").contains("Mac")){
                if (browser.equalsIgnoreCase("Firefox")){
                    System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir")+BrowserConfigurations.fireFoxDriverLocationMAC);
                    driver= new FirefoxDriver();
                    driver.manage().window().maximize();
                }
                else if (browser.equalsIgnoreCase("Chrome")){
                    System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+BrowserConfigurations.googleChromeDriverLocationMAC);
                    ChromeOptions option = new ChromeOptions();
                    option.addArguments("disable-extensions");
                    option.addArguments("--start-maximized");
                    driver = new ChromeDriver(option);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            wutil=new webDriverUtil(driver);
            commonpage=new commonPage(driver);
            commoncomp=new commonComp(driver);
        }
        return driver;
    }

    public void getResult(String stepName, String status) throws IOException {
        if (status.equalsIgnoreCase("Fail")) {
            childTest.log(Status.FAIL, MarkupHelper.createLabel("Step:- '"+stepName+"' FAILED due to below issues:",ExtentColor.RED));
            childTest.log(Status.FAIL, Arrays.toString((new Throwable()).getStackTrace()));
            childTest.log(Status.FAIL, "Snapshot below: ", MediaEntityBuilder.createScreenCaptureFromPath(wutil.getScreenshotWhereRequired(reusableLibrary.stpNmeScrnshtCreatn(stepName))).build());
            //childTest.addScreenCaptureFromPath(wutil.getScreenshotWhereRequired(reusableLibrary.stpNmeScrnshtCreatn(stepName)));
        } else if (status.equalsIgnoreCase("Pass")) {
            childTest.log(Status.PASS, MarkupHelper.createLabel("Step:- '"+stepName + "' PASSED", ExtentColor.GREEN));
            childTest.log(Status.PASS, "Snapshot below: ", MediaEntityBuilder.createScreenCaptureFromPath(wutil.getScreenshotWhereRequired(reusableLibrary.stpNmeScrnshtCreatn(stepName))).build());
        } else {
            childTest.log(Status.SKIP, MarkupHelper.createLabel("Step:- '"+stepName+ "' Test Case SKIPPED because the previous step failed", ExtentColor.ORANGE));
            //childTest.log(Status.SKIP, Arrays.toString((new Throwable()).getStackTrace()));
        }
    }

}
