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
    And I add a new product with a "<name>" and "<price>"
    And I see the product is visible when I view all products
    When I request the products details using its product "<id>"
    Then I receive the details of the product
    #This needs an after step to clear itself down
    Examples:
      | id | name | price |
      | 10 | Mug  | 9.99  |
  @updateProduct @cleanUp
  Scenario Outline: Create and update a product
    Given I have access to the Sensyne test API
    And I add a new product with a "<name>" and "<price>"
    And I see the product is visible when I view all products
    When I request the products details using its product <code>
    Then I receive the details of the product and can see the <type> is now <value>
        #This needs an after step to clear itself down
    Examples:
    | id | name | price | type | value |

  @deleteProduct
  Scenario Outline: Create and Delete a product
    Given I have access to the Sensyne test API
    And I add a new product with a "<name>" and "<price>"
    And I see the product is visible when I view all products
    When I delete the product "<name>"
    Then I see the product is not visible when I view all products
    Examples:
      | name | price |
      | Cup  | 9.99  |




