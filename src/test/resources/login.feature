Feature: SauceDemo Login

  Scenario: Successful login with valid credentials
    Given user is on the SauceDemo login page
    When username "standard_user" and password "secret_sauce" are entered
    And the login button is clicked
    Then user should be redirected to the Products page

  Scenario: Failed login with invalid credentials
    Given user is on the SauceDemo login page
    When username "wrong" and password "bad" are entered
    And the login button is clicked
    Then an error message "Epic sadface: Username and password do not match any user in this service" should be displayed

  Scenario: Username is empty
    Given user is on the SauceDemo login page
    When username "" and password "secret_sauce" are entered
    And the login button is clicked
    Then an error message "Epic sadface: Username is required" should be displayed

  Scenario: Password is empty
    Given user is on the SauceDemo login page
    When username "standard_user" and password "" are entered
    And the login button is clicked
    Then an error message "Epic sadface: Password is required" should be displayed
