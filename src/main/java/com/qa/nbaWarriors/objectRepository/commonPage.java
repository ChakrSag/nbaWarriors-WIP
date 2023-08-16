package com.qa.nbaWarriors.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class commonPage {
    private WebDriver driver=null;

    public commonPage(WebDriver driver){
        this.driver=driver;
    }
    protected By buttonToSelectTeam=By.xpath("//button[@data-testid='button-teams']");
    protected By allPresentTeams=By.xpath("//ul[@id='teams']//a");

    protected By allPresentTeamsAsDropDown=By.id("teams");

    public List<WebElement> get_stateHeaderForAllCityTeams(String state){
        By stateHeader=By.xpath("//span[contains(text(), '"+state+"')]//following-sibling::ul//a");
        List<WebElement> stateHeaderForAllCityTeams=driver.findElements(stateHeader);
        return stateHeaderForAllCityTeams;
    }

    public String getCityNameAsText(WebElement cityName){
        return cityName.getText();
    }

    public String getTeamNameAsText(WebElement cityName){
        return cityName.getAttribute("href").substring(1);
    }
    public WebElement get_ButtonToSelectTeam() {
        WebElement getButtonToSelectTeam=driver.findElement(buttonToSelectTeam);
        return getButtonToSelectTeam;
    }

    public WebElement get_AllPresentTeams() {
        WebElement getAllPresentTeams=driver.findElement(allPresentTeams);
        return getAllPresentTeams;
    }

    public WebElement get_AllPresentTeamsAsDropDown() {
        WebElement getAllPresentTeamsAsDropDown=driver.findElement(allPresentTeamsAsDropDown);
        return getAllPresentTeamsAsDropDown;
    }

}
