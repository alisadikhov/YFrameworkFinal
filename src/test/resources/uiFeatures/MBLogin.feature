Feature: Login Page

  Background:
    Given I’m on "MealB.loginpage"

  @MBAT-4 @Positive
  Scenario: User should be able to login with valid credentials

    And I enter User Name "MealB.empUserName"
    And I enter Password "MealB.empPassword"
    When I click log in button
    Then I land on "MealB.loginpage"

  @MBAT-4 @Negative
  Scenario Outline: I should get login failed message when attempting to with invalid credentials

    And I enter invalid User Name "<username>"
    And I enter invalid Password "<password>"
    When I click log in button
    Then I receive Login Failed message box
    Then I can click on OK button to get back to login screen

    Examples:
      | username   | password  |
      | someone    | something |
      | employee_1 | blabla    |
      | blabla     | 555@raid  |

