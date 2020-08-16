Feature: Testing a REST API - weighted average LIBOR
  Users should be able to submit POST requests to a weighted average LIBOR service,


  @SmokeTest
  Scenario Outline: Validating Calculations
   Given Trade Date is <tradedate>
   And Due Date is <duedate>
   And Extension Date is <extensiondate>
   When weighted average LIBOR is calculated
   Then validate the <response> with statusCode <response_code>

    Examples:
      | tradedate  |     duedate    | extensiondate |      response                                | response_code  |
      |"2020-07-10"|   "2020-08-10" | "2020-09-11"  |    "64.03174603174602"                       |   "200"        |
      |"2020-07-10"|   "2020-06-10" | "2020-05-10"  |    "Due date cannot be after extension date" |   "400"        |
      |"2020-05-10"|   "2020-04-10" | "2020-06-10"  |    "Trade date cannot be after Due date"     |   "400"        |
      |"03-03-2020"|   "2020-04-10" | "2020-06-10"  |    "Trade date is not a valid date"          |   "400"        |
      |"2020-05-10"|   "03-03-2020" | "2020-06-10"  |    "Due date is not a valid date"            |   "400"        |
      |"2020-05-10"|   "2020-04-10" | "2020-06-10"  |    "Extension date is not a valid date"      |   "400"        |
      |"xyz"       |   "2020-04-10" | "2020-06-10"  |    "Trade date is not a valid date"          |   "400"        |

  @SmokeTest
  Scenario: Validating Authorisation
    Given Trade Date is "2020-07-10"
    And Due Date is "2020-08-10"
    And Extension Date is "2020-08-10"
    When weighted average LIBOR is calculated with invalid token
    Then request should be rejected

  @SmokeTest
  Scenario: Validating content type
    Given Trade Date is "2020-07-10"
    And Due Date is "2020-08-10"
    And Extension Date is "2020-08-10"
    When weighted average LIBOR is calculated with invalid content type
    Then request should be unsupported content type