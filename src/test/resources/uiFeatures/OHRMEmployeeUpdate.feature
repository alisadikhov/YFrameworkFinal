Feature: OHRM employee update


  @HRMTest
  Scenario:
    Given  I'm on logged in to OrangeHRM as admin
    And I click on PIM>Add Employee
    Then I feel out "FirstName","LastName"
    When I click on "Create login details"
    Then I see additional fields displayed
    When I click on "Create login details"
    And  The fields are no longer displayed