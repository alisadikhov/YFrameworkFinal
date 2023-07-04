Feature: End to End API scenario

  @E2E   @APIRegression  @APISmoke
  Scenario Outline: End2End scenario: Validate elements in CreateExpense response

    Given User gets token for "company" account when flag is "<flags>"
    Then Create request body "Elshan" , "8899", "07/06/2020 01:36:29","3333" for expense API
    And User submits POST request to "CreateExpense_URL" api
    And User validates if the status code is 200
    When User retrieves "ID" from response
    Then User validates "<elements>" in response
    And User submits request to delete expense with record ID
    When User submit GET request to "GetAllExpenses_URL"
    Then User validates if the record was deleted

    Examples:
      | flags | elements              |
      | true  | id                    |
      | false | name                  |
      | false | amount                |
      | false | expenseType           |
      | false | expenseDateTime       |
      | false | businessPurpose       |
      | false | natureOfGift          |
      | false | giftRecipient         |
      | false | vendorName            |
      | false | destinationOfTravel   |
      | false | company               |
      | false | projectName           |
      | false | longitude             |
      | false | latitude              |
      | false | placeName             |
      | false | placeAddress          |
      | false | placeId               |
      | false | placeIcon             |
      | false | expenseRelationshipId |
      | false | travelExpenseTypeId   |
      | false | otherExpenseNameId    |
      | false | otherRelationship     |
      | false | receiptFile           |
      | false | receiptUrl            |

 
 
 