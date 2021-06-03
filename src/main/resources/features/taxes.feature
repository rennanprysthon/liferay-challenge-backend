
Feature: calculate percent of a product
  Scenario Outline: when given values should calculate based on his type
    Given one product of type '<type>', value <value>, imported '<imported>'
    When calculate percent
    Then be equals to <expected>
    Examples:
      | type | value | imported | expected |
      |  BO  | 12.49 |  false   | 12.49    |
      |  MU  | 10.0  |  true    | 12.00    |

  Scenario: when given values should calculate their percentual
    Given multiply
      | name      | type | value   | imported |
      | book      | BO   | 12.49   | false    |
      | musicCD   | OT   | 14.99   | false    |
      | chocolate | FO   | 0.85    | false    |
    When calculate all values
    Then sales taxes should be <'1.50'> and total value should be <'29.83'>