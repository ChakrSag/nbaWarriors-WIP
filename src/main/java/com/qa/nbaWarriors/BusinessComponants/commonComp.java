package com.qa.nbaWarriors.BusinessComponants;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qa.nbaWarriors.SupportLibrary.webDriverUtil;
import com.qa.nbaWarriors.objectRepository.commonPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.Set;

public class commonComp extends webDriverUtil {
    commonPage commonpage;
    public commonComp(WebDriver driver) {
        super(driver);
        commonpage=new commonPage(this.driver);
    }

    public void tapOnTeamsDropdownAndMakeItVisible(ExtentTest childTest){
        try {
            //Wait for application to launch
            hardWait();
            //System.out.println("Kaka");
            if(explicitWaitforElementClickable(commonpage.get_ButtonToSelectTeam())) {
                clickObject(commonpage.get_ButtonToSelectTeam());
                //fillData(commonpage.get_password(), dataset.getCommonData(scenarioName, "createPassword"));
                logEvent.info("Successfully tapped on the Team Dropdown");
                childTest.log(Status.PASS, MarkupHelper.createLabel("Successfully tapped on the Team Dropdown", ExtentColor.GREEN));
            }

            else {
                logEvent.error("Could not able to tap on the Team Dropdown");
                childTest.log(Status.FAIL, MarkupHelper.createLabel("Could not able to tap on the Team Dropdown", ExtentColor.RED));
            }

            if(explicitWaitforElementVisible(commonpage.get_ButtonToSelectTeam())) {
                makeElementVisible(commonpage.get_ButtonToSelectTeam());
                //fillData(commonpage.get_password(), dataset.getCommonData(scenarioName, "createPassword"));
                logEvent.info("Successfully made the Dropdown visible");
                childTest.log(Status.PASS, MarkupHelper.createLabel("Successfully made the Dropdown visible", ExtentColor.GREEN));
            }

            else {
                logEvent.error("Could not able to make the Dropdown visible");
                childTest.log(Status.FAIL, MarkupHelper.createLabel("Could not able to make the Dropdown visible", ExtentColor.RED));
            }
        } catch (Exception e) {
            // TODO: handle exception
            //System.out.println("Nunu");
            logEvent.fatal(e.toString());
            childTest.log(Status.FAIL, e.toString());
        }

    }

    public void checkTeamWebsiteIsOpening(String scenarioName, String stateName, ExtentTest childTest){
        try{
            for(WebElement aParticularCity : commonpage.get_stateHeaderForAllCityTeams(stateName)){
                if(commonpage.getTeamNameAsText(aParticularCity).equals(dataset.getCommonData(scenarioName,commonpage.getCityNameAsText(aParticularCity))))
                {
                    logEvent.info("City to Team mapping is working successfully");
                    childTest.log(Status.PASS, MarkupHelper.createLabel("City to Team mapping is working successfully", ExtentColor.GREEN));
                }
                else{
                    logEvent.info("City to Team mapping is having issues");
                    childTest.log(Status.PASS, MarkupHelper.createLabel("City to Team mapping is having issues", ExtentColor.RED));
                }
                clickObject(aParticularCity);
                Set<String> getAllOpenTabs=driver.getWindowHandles();
                Iterator<String> itr=getAllOpenTabs.iterator();
                boolean found=false;

                while (itr.hasNext()){
                    if(driver.getCurrentUrl().substring(7).equals(dataset.getCommonData(scenarioName,commonpage.getCityNameAsText(aParticularCity)))){
                        logEvent.info("We landed onto the current team for a City");
                        childTest.log(Status.PASS, MarkupHelper.createLabel("We landed onto the current team for a City", ExtentColor.GREEN));
                        found=true;
                        break;
                    }else {
                        driver.switchTo().window(driver.getWindowHandle());
                    }
                }

                if(!found){
                    logEvent.error("Could not landed onto the proper team for a City");
                    childTest.log(Status.FAIL, MarkupHelper.createLabel("Could not landed onto the proper team for a City", ExtentColor.RED));
                }

            }
        }catch (Exception e) {
            // TODO: handle exception
            //System.out.println("Nunu");
            logEvent.fatal(e.toString());
            childTest.log(Status.FAIL, e.toString());
        }

    }

}
