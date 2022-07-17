Feature: User
  As an admin
  I want to get detail user
  So that I can update the user data

  Scenario Outline: POST - As an admin I have to be able to create new user
    Given I set an endpoint for POST new "<username>" with "<password>"
    When I request POST detail user
    Then I validate the status code is <status_code>
    And validate the "<message>" after create user
    And get userId if "<message>" for other request
    Examples:
    | username | password | status_code | message |
    | new       | Password1234! | 201   | success |
    |           | Password1234! | 400   | required |
    | aisyahns22       |               | 400   | required |
    |           |               | 400   | required  |
    | same      | Password1234! | 406   | existed   |

  Scenario: POST - As a user I have to be able to generate token
    Given I set an endpoint for POST generate token
    When I request POST generate token
    Then I validate the status code is 200
    And validate the data detail after generate token
    And get token for other request

  Scenario: GET - As a user I have to be able to get detail user
    Given I set an endpoint for GET detail user
    When I request GET detail user
    Then I validate the status code is 200
    And validate the data detail

  Scenario: GET - As a user I have to authorized myself
    Given I set an endpoint for authorized myself
    When I request POST authorized myself
    Then I validate the status code is 200
    And validate the data detail for authorized