Feature: Token API: TimeOut test

  @Timeout
  Scenario Outline: Timeout test
    Given Creates request data with "<username>" and "<password>"
    And  Submits request to TOKEN api
    Examples:
      | username | password |
      | yoll     | Test123! |