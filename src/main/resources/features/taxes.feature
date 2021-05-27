
  Feature: calculate percent of a product
    Scenario Outline: when given values should calculate based on his type
      Given one product of type '<type>', value <value>, imported '<imported>'
      When calculate percent
      Then be equals to <expected>
      Examples:
        | type | value | imported | expected |
        |  BO  | 12.49 |  false   | 12.49    |
        |  OT  | 14.99 |  false   | 16.49    |
        |  FO  | 0.85  |  false   | 0.85     |
        |  FO  | 10.00 |  true    | 10.50    |
        |  OT  | 47.50 |  true    | 54.65    |
        |  OT  | 27.99 |  true    | 32.19    |
        |  OT  | 18.99 |  false   | 20.89    |
        |  ME  | 9.75  |  false   | 9.75     |
        |  FO  | 11.25 |  true    | 11.85    |

    Scenario: when given values should calculate their percentual
      Given multiply
        | name      | type | value   | imported |
        | book      | BO   | 12.49   | false    |
        | musicCD   | OT   | 14.99   | false    |
        | chocolate | FO   | 0.85    | false    |
      When calculate all values
      Then sales taxes should be <'1.50'> and total value should be <'29.83'>