package com.qa.nbaWarriors.SupportLibrary;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.qa.nbaWarriors.browserSelector.selectBrowser;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qa.nbaWarriors.readProperty.*;
import com.qa.nbaWarriors.jSonUtil.jSonTestDataReader;

public abstract class reusableLibrary {
    protected Logger logEvent=Logger.getLogger(getClass().getSimpleName());
    protected readPropertyFileData dataReadFromPropertyFile=new readPropertyFileData();
    protected selectBrowser browserToLaunch=new selectBrowser();
    protected jSonTestDataReader dataset=new jSonTestDataReader();

    public abstract void hardWait();
    public abstract boolean explicitWaitforElementClickable(WebElement element);
    public abstract boolean explicitWaitforElementVisible(WebElement element);

    static String getCurrentDateandTime(){
        DateFormat dateFormat=new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss a");
        Date date=new Date();
        String dateString = dateFormat.format(date);

        return dateString;
    }

    public File createDirectory(String directoryName){

        File directoryfortheDay=new File(directoryName);

        if (!directoryfortheDay.exists()) {
            //logEvent.info("Directory creation takes place for Reports" + directoryfortheDay);
            directoryfortheDay.mkdir();
            //System.out.println("**************Created************");
        }

        else{
            //logEvent.info(directoryfortheDay+ " already created directory");
            //System.out.println("**************Already Created************");
            /**
             * Do nothing
             */
        }

        return directoryfortheDay;
    }

    public String classNameSplit(String className){

        String actualclass[]=className.split("\\.");
        //logEvent.info("Required class name:- "+actualclass[3]);
        return actualclass[3];
    }

    public String testDataSeperator(String globalTestData){

        String actualTestdata[]=globalTestData.split("#");
        //logEvent.info("Required class name:- "+actualTestdata[1]);
        return actualTestdata[1];
    }

    public static boolean ifStringhasSpace(String test) {
        boolean containSpace=false;
        if(test.contains(" ")) {
            containSpace=true;
        }
        return containSpace;
    }

    public static String scenarioNameforTData(String scenarioName) {
        String revisedScenarioName=null;
        if(scenarioName.contains(" ")) {
            revisedScenarioName=scenarioName.replaceAll("\\s+", "");
        }
        return revisedScenarioName;
    }

    public static String TestDataKeySeperator(String testDataKeySet) {

        if(testDataKeySet.contains(" ")) {
            String revisedTestDataKeySet=null;
            revisedTestDataKeySet=testDataKeySet.replaceAll("\\s+", "");
            return revisedTestDataKeySet;
        }else{
            return testDataKeySet;
        }

    }

    public static String featrNmeReportCreatn(String scenarioID) {
        String featureFolder[]=scenarioID.split("\\;");
        String aclFetrNme=featureFolder[0].replaceAll("-", "_");
        return aclFetrNme;
    }

    public static String stpNmeScrnshtCreatn(String actualStepName) {
        String revisedStepName=null;
        if(actualStepName.contains(" ")) {
            revisedStepName=actualStepName.replace('"', ' ');
        }
        if(revisedStepName.contains(" ")) {
            revisedStepName=revisedStepName.replaceAll("\\s+", "_");
        }
        return revisedStepName;
    }
}
