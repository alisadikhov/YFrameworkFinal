Feature: Get Monthly Expenses

  @testing
  Scenario:
    Given User gets token for "company" account when
    When User submit GET request to "http://34.235.0.4/api/expenses/getMonthlyExpenses" api
    Then User validates if the status code is 200
