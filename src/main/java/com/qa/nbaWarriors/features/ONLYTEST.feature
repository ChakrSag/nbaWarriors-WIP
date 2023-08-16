Feature: Team Name to State Name mapping
  Team Names under Teams drop down menu bar will load the respective team page


  Scenario Outline: Ensure page Corresponding to a Team is opening when we tap on the Team City

    Given User launches the Warrior Team URL in Browser
    When User clicks on Team Button
    Then User should be able to select a team under a particular "<State>" and Home page of that particular Team should open


    Examples:
      |State|
      |Atlantic|
      |Central |
      |Southeast|
