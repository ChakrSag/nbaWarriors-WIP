package com.qa.nbaWarriors.SupportLibrary;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class webDriverUtil extends reusableLibrary{
    protected WebDriver driver=null;

    public webDriverUtil (WebDriver driver) {
        this.driver=driver;
    }

    public boolean validateElement(WebElement element) {
        boolean emailFildLod=false;
        if(explicitWaitforElementVisible(element)) {
            //logEvent.info("Successfully loaded PA login page");
            emailFildLod=true;
        }
        else {
            //logEvent.fatal("PA Page did not load properly");
        }

        return emailFildLod;
    }

    public void hardWait(){
        int waitTime=(Integer.parseInt(dataReadFromPropertyFile.readData("maxintegertimeout")))/10;
        try{

            for (int i=1;i<=waitTime;i++)
                //Thread.sleep(1000*i);
                driver.manage().timeouts().implicitlyWait(10*i, TimeUnit.SECONDS);
            //logEvent.info("Hard wait:-waited for "+waitTime+" seconds");
        }catch(Exception E){
            //logEvent.error("Could not wait for element:- "+E);
        }
    }

    public boolean explicitWaitforElementClickable(WebElement element){
        boolean elementClickable=false;
        try{
            int waitTime=Integer.parseInt(dataReadFromPropertyFile.readData("maxintegertimeout"));
            try{
                WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(waitTime));
                wait.until(ExpectedConditions.elementToBeClickable(element));
                elementClickable=true;
                //logEvent.info("Waited for Element to be clickable");
            }catch(ElementNotInteractableException E){
                //logEvent.error("Could not explicitly wait for element to be clicked:- "+E);
                elementClickable=false;
            }
        }catch(Exception E){
            //logEvent.error("Wait time could not be initialized:- "+E);
            elementClickable=false;
        }
        return elementClickable;
    }

    public boolean explicitWaitforElementVisible(WebElement element){
        boolean elementFound=false;
        try{
            int waitTime=Integer.parseInt(dataReadFromPropertyFile.readData("maxintegertimeout"));
            try{
                WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(waitTime));
                wait.until(ExpectedConditions.visibilityOf(element));
                elementFound=true;
                //logEvent.info("Waited for Element to be found");
            }catch(ElementNotInteractableException E){
                //logEvent.error("Could not explicitly wait for element to be visible:- "+E);
                elementFound=false;
            }
        }catch(Exception E){
            //logEvent.error("Wait time could not be initialized:- "+E);
            elementFound=false;
        }

        return elementFound;
    }

    public String getScreenshotWhereRequired(String testName){

        String path=null;
        //final String fileSeperator = System.getProperty("file.separator");

        String directoryPath=System.getProperty("user.dir") +"/ScreenShots";

        TakesScreenshot ts=(TakesScreenshot)driver;

        if(driver instanceof TakesScreenshot){

            //File directory=new File("E:/SeleniumSBA/selenium.maven.deom/ScreenShots"+fileSeperator+testName);

            //Checking whether directory for test name is present
            File directoryExistance=createDirectory(directoryPath+"/"+testName);
            //logEvent.info("Actual folder created to push screenshots");
            //File directory=new File(directoryPath+"/"+testName);

				/*if (!directory.exists()) {
					log.info("Directory creation takes place for screenshots" + directory);
					directory.mkdir();
				}

				else{
					log.info(directory+ " already created directory");
				}*/

            File srcFile=ts.getScreenshotAs(OutputType.FILE);

            path=directoryExistance+"/screenshot__"+getCurrentDateandTime()+".jpg";

            //path="E:/SeleniumSBA/selenium.maven.deom/ScreenShots/screenshot__"+System.currentTimeMillis()+".png";



            File destination= new File(path);

            try{
                FileUtils.copyFile(srcFile, destination);

            }catch (IOException E){

                //logEvent.error("Screen capture copy failed "+E);
            }
        }

        else{
            //logEvent.error("Issues to add add screenshot to file due to ClassCastException");
        }

        return path;
        //System.out.println(path);
    }

    public void makeElementVisible(WebElement element){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style','visibility:visible;');",
                element);
    }

    public void fillData(WebElement element,String data){
        try{
            element.click();
            element.clear();
            element.sendKeys(data);
            //logEvent.info("Data sent to Element successfully");
        }catch(ElementNotInteractableException E){
            //logEvent.error("data could not be filled up due to:- "+E);
        }
    }
    public void clickObject(WebElement element){
        try{
            element.click();
            //logEvent.info("Element is clicked successfully");
        }catch(ElementNotInteractableException E){
            //logEvent.error("element not interacting to click operation:- "+E);
        }
    }
}
