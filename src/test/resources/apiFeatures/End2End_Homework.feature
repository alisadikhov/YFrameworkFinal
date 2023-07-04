Feature: End to End scenario HOMEWORK

  @Homework @E2E   @APIRegression  @APISmoke
  Scenario Outline: End2End scenario: Validate value of elements in CreateExpense response


    Given User gets token for "company" account when flag is "<flags>"
    Then User creates request body "<name>" , "<giftRecipient>", "<expenseDateTime>","<amount>" for expense API
    And User submits POST request to "CreateExpense_URL" api
    And User validates if the status code is 200
    And User validates value of "<name>" , "<giftRecipient>", "<expenseDateTime>","<amount>" in response
    When User retrieves "ID" from response
    And User submits request to delete expense with record ID


    Examples:
      | flags | name        | giftRecipient | expenseDateTime     | amount  |
      | true  | YollStudent | 5566          | 08/30/2020 01:36:29 | 99000.0 |


