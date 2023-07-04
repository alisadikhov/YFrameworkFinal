Feature: Expenses Page

  Background:
    Given I’m on "MealB.loginpage"
    And I enter User Name "MealB.empUserName"
    And I enter Password "MealB.empPassword"
    And I click log in button
    When I click on Expenses Tab
    Then I am navigated to expenses page

  @MBAT-23
  Scenario:
    And I see table with default expense details headers
      | headers          |
      | Expense name     |
      | Amount           |
      | Type             |
      | Expense date     |
      | Business purpose |
      | ProjectBDF name  |
      | Actions          |


  @MBAT-23
  Scenario:
    When I click on the List Dropdown
    Then I see list of all table headers
      | headers          |
      | Expense name     |
      | Amount           |
      | Type             |
      | Expense date     |
      | Business purpose |
      | Project name     |

  @MBAT-23
  Scenario:
    When I unselect all the options from dropdown list
    Then Table headers are removed as well