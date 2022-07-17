Feature: Book

  Scenario: Get list book
    Given I set endpoint for get list book
    When I request get list book
    Then I verify status code is 200
    And get isbn data for other request

  Scenario: Add ISBN to user
    Given I am doing login
    When I request add isbn to user
    Then I verify the status code is 201
    And I validate the data detail

  Scenario: Delete ISBN from user
    Given I set endpoint for delete isbn
    When I request delete isbn to user
    Then I verify the status code is 204
    And I validate the data detail for delete