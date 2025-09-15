Feature: API Automation Sample

  Scenario: Get user list from API
    Given the API endpoint is "https://reqres.in/api/users/2"
    When I send a GET request
    Then the response status code should be 200
    And the response should contain "Janet"
