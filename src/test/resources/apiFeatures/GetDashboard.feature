Feature: Get Dashboard

  @DashboardAPI  @APIRegression  @APISmoke
  Scenario Outline: Post Once  validate multiple times

    Given User gets token for "company" account when flag is "<flags>"
    When User submit GET request to "Dashboard_URL"
    And User validates if the status code is 200
    Then User validates "<elements>" in response

    Examples:
      | flags | elements                     |
      | true  | otherExpenses                |
      | false | mealAndEntertainmentExpenses |
      | false | travelExpenses               |
      | false | giftExpenses                 |
      | false | deductibleGiftExpenses       |
      | false | taxSaving                    |
      | false | numberOfEmployees            |
