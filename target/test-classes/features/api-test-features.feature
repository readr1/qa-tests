@apiTests
Feature: API technical tests

  Scenario: Get all products
    Given I have access to the Sensyne test API
    When I request a list of all products
    Then I receive a list of all products containing an name, product code and price