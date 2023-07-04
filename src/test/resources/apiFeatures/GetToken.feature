Feature: Token API

  @tokenApi  @regression  @smoke
  Scenario Outline: Get token
    Given User creates request data with "<username>" and "<password>"
    And User submits request to TOKEN api
    And User validates if status code is 200
    Then User retrieves access token from response

    Examples:
      | username | password |
      | yoll     | Test123! |




#@tokenApi  @regression  @smoke
#Scenario Outline: Negative Scenario- UserName is missing
#
#Given User creates request data with "<username>" and "<password>"
#And User submits request to TOKEN api
#And User validates if status code is 400
#Then User valitates "<errorMessage>"
#Examples:
#|username | password |        	errorMessage  				            	|
#|         |	Test123! | The UserNameOrEmailAddress field is required.|
#
#
#
#
#@tokenApi  @regression  @smoke
#Scenario Outline: Negative Scenario- UserName is missing
#
#Given User creates request data with "<username>" and "<password>"
#And User submits request to TOKEN api
#And User validates if status code is 400
#Then User valitates "<errorMessage>"
#Examples:
#|username | password |        	errorMessage  				    |
#| yoll    |	         | The Password field is required.		|


  @tokenApi  @regression  @smoke
  Scenario Outline: Negative Scenario- "<reason>"

    Given User creates request data with "<username>" and "<password>"
    And User submits request to TOKEN api
    And User validates if status code is 400
    Then User valitates "<errorMessage>"

    Examples:

      | username | password | errorMessage                                  | reason              |
      |          | Test123! | The UserNameOrEmailAddress field is required. | UserName is missing |
      | yoll     |          | The Password field is required.               | Password is missing |


  @tokenApi  @regression  @smoke
  Scenario Outline: Negative Scenario- "<reason>"

    Given User creates request data with "<username>" and "<password>"
    And User submits request to TOKEN api
    And User validates if status code is 500
    Then User valitates "<errorMessage>"

    Examples:

      | username | password | errorMessage                 | reason            |
      | yolllll  | Test123! | Invalid username or password | Username is wrong |
      | yoll     | TTTTT    | Invalid username or password | Password is wrong |







