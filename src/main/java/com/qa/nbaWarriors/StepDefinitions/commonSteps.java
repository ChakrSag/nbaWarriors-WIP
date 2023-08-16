package com.qa.nbaWarriors.StepDefinitions;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qa.nbaWarriors.Reporting.ExtentManager;
import com.qa.nbaWarriors.Reporting.ExtentManagerAssociateFunctions;
import com.qa.nbaWarriors.SupportLibrary.reusableLibrary;
import com.qa.nbaWarriors.browserSelector.selectBrowser;
import cucumber.api.Scenario;
import cucumber.runtime.StepDefinitionMatch;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import com.qa.nbaWarriors.Reporting.ThreadLocalStepDefinitionMatch;
import io.cucumber.java.en.When;

public class commonSteps extends selectBrowser {
    private static boolean initialized = false;

    @Before
    public static void setUp(Scenario scenario) throws Exception{
        //reportDirectoryCheck=ExtentManagerAssociateFunctions.createDirectory(System.getProperty("user.dir") +"/Reports");
        if (!initialized) {
            // Init context. Run just once before first scenario starts
            suiteName = reusableLibrary.featrNmeReportCreatn(scenario.getId());
            reportDirectoryCheck= ExtentManagerAssociateFunctions.createDirectory(System.getProperty("user.dir") +"/Reports/Reports__"+ExtentManagerAssociateFunctions.getCurrentDate());
            String fileName=reportDirectoryCheck+"/"+suiteName+"__"+ExtentManagerAssociateFunctions.getCurrentDateandTime()+".html";
            extent = ExtentManager.createInstance(fileName);
            extent.setSystemInfo("OS", "MAC");
            //extent.setSystemInfo("Host Name", "CI");
            extent.setSystemInfo("Environment", "Production");
            extent.setSystemInfo("Creator", "Sagar Chakraborty");
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    // End context. Run just once after all scenarios are finished
                    extent.flush();
                }
            });
            initialized = true;
        }

        //System.out.println("Sagar I M:- "+reusableLibrary.scenarioNameforTData(scenario.getName()));
        scenarioName=reusableLibrary.scenarioNameforTData(scenario.getName());
        parentTest = extent.createTest(scenario.getName());
        //System.out.println("SagarXXXX:- "+reusableLibrary.featrNmeReportCreatn(scenario.getId()));
    }

    @Given("^User launches the Warrior Team \\\"([^\\\"]*)\\\" in Browser$")
    public void user_launches_the_Warrior_Team_in_Browser(String URL) throws Exception {
        StepDefinitionMatch match = ThreadLocalStepDefinitionMatch.get();
        childTest=parentTest.createNode(match.getStepName());

        try{
            if(!nextsteptobeSkipped) {
                getBrowser("Chrome");
                driver.get(dataReadFromPropertyFile.readData("PrimaryURL"));
                childTest.log(Status.PASS, MarkupHelper.createLabel("Warrior Team page launched properly", ExtentColor.GREEN));
                getResult(match.getStepName(), dataReadFromPropertyFile.readData("Passed"));
            }
        }catch (Exception e){
            childTest.log(Status.FAIL, MarkupHelper.createLabel("Warrior Team page did not launch properly", ExtentColor.RED));
            getResult(match.getStepName(), dataReadFromPropertyFile.readData("Failed"));
            nextsteptobeSkipped=true;
        }

    }

    @When("^User clicks on Team Button$")
    public void user_clicks_on_Team_Button(String URL) throws Exception {
        StepDefinitionMatch match = ThreadLocalStepDefinitionMatch.get();
        childTest=parentTest.createNode(match.getStepName());
        try{
            if(!nextsteptobeSkipped) {
                commoncomp.tapOnTeamsDropdownAndMakeItVisible(childTest);
                childTest.log(Status.PASS, MarkupHelper.createLabel("User was able to click on Teams button successfully", ExtentColor.GREEN));
                getResult(match.getStepName(), dataReadFromPropertyFile.readData("Passed"));
            }
        }catch (Exception e){
            childTest.log(Status.FAIL, MarkupHelper.createLabel("User had issues to click on Team Button", ExtentColor.RED));
            getResult(match.getStepName(), dataReadFromPropertyFile.readData("Failed"));
            nextsteptobeSkipped=true;
        }
    }

    @Given("^User should be able to select a team under a particular \"([^\"]*)\" and Home page of that particular Team should open$")
    public void user_should_be_able_to_select_Team(String State) throws Exception {
        StepDefinitionMatch match = ThreadLocalStepDefinitionMatch.get();
        childTest=parentTest.createNode(match.getStepName());
        try{
            if(!nextsteptobeSkipped) {
                commoncomp.checkTeamWebsiteIsOpening(scenarioName, State, childTest);
                childTest.log(Status.PASS, MarkupHelper.createLabel("User was able to navigate to a specific Team's page", ExtentColor.GREEN));
                getResult(match.getStepName(), dataReadFromPropertyFile.readData("Passed"));
            }
        }catch (Exception e){
            childTest.log(Status.FAIL, MarkupHelper.createLabel("User had issues to navigate to a specific Team's page", ExtentColor.RED));
            getResult(match.getStepName(), dataReadFromPropertyFile.readData("Failed"));
            nextsteptobeSkipped=true;
        }
    }


    @After
    public void tearDown(Scenario scenario) {
        if(driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
            driver = null;
        }

        nextsteptobeSkipped=false;

    }
}
