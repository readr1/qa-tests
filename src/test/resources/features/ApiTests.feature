@apiTests
Feature: API technical tests

  @getAll
  Scenario: Get all products
    Given I have access to the Sensyne test API
    When I request a list of all products and get a successful response
    Then I receive a list of all products containing an name, product code and price

  @getProduct @cleanUp
  Scenario Outline: Create and Get a product
    Given I have access to the Sensyne test API
    When I add a new product with a "<name>" and "<price>"
    Then I see the product is visible when I view all products
    And I delete the product

    Examples:
      | name | price |
      | Mug  | 9.99  |

  @updateProduct @cleanUp
  Scenario Outline: Create and update a product
    Given I have access to the Sensyne test API
    And I add a new product with a "<name>" and "<price>"
    And I see the product is visible when I view all products
    When I update the product "<newName>" to have a price of "<newPrice>"
    Then I can see the product has a new "<newName>" and "<newPrice>"
    And I delete the product "<newName>"

    Examples:
    | name  | price | newName | newPrice |
    | glass | 2.99  | glasses | 3.99     |

  @deleteProduct
  Scenario Outline: Create and Delete a product
    Given I have access to the Sensyne test API
    And I add a new product with a "<name>" and "<price>"
    And I see the product is visible when I view all products
    When I delete the product
    Then I see the product is not visible when I view all products

    Examples:
      | name | price |
      | Cup  | 9.99  |
