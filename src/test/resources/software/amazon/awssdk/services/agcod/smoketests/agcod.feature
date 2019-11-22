# language: en
@smoke @agcod
Feature: Amazon AGCOD

  Scenario: Making a request
    When I call the "CreateGiftCard" API with:
      | Limit | 1 |
    Then the value at "GcClaimCode" should be a string

  Scenario: Handling errors
    When I attempt to call the "DescribeTable" API with:
      | TableName | fake-table |
    Then I expect the response error code to be "ResourceNotFoundException"
