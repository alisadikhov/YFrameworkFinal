Feature: MealB Personal Details

  Background:
    Given I am logged in to application
    And I click on personal Details tab
    And I am on Personal Details page

#@MBAT-17
  Scenario: Required Fields Pre-fill Validation

    Then I see all the required fields pre-filled
      | fields       |
      | Name         |
      | EmailAddress |
      | Surname      |
      | UserName     |
      | CompanyName  |
    And field is displayed with value as read only
      | field        |
      | EmailAddress |
      | UserName     |
      | CompanyName  |

  #@MBAT-17
  Scenario Outline: Error Message Validation for missing required fields

    And I don’t have values for all "<required fields>"
    When I click on the update button
    Then I get a “This field is required” message for each required field

    Examples:
      | required fields |
      | Name            |
      | Surname         |

  #@MBAT-17
  Scenario: Error Message Validation for incorrect password confirmation input

    And I enter new value "something" in password field
    But I don’t enter same value "something123" on confirm password field
    When I click on the update button
    Then I see message “Password confirmation doesn't match Password”

  #@MBAT-17
  Scenario Outline: Verify the phone Number length

    Then I should be able to enter only 10 "<numbers>" in Phone Number field
    Examples:
      | numbers       |
      | 1234567890    |
      | 5555555555555 |
      | 52321516      |
      | 560           |


  @MBAT-17
  Scenario Outline: Verify the postal Code input limitations

    Then I should be able to enter only number "<elements>" to the Postal Code field
    Examples:
      | elements  |
      | 132465    |
      | something |
      | 123digits |

