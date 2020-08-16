Feature: Date Validation

  # Sceanrio out line gives the vairables access
  Scenario Outline: Validate Dates
    Given Due Date <due_date>
    And Trade Date <trade_date>
    And Extension Date <extension_date>
    When dates are checked
    Then error count should be <err_count>

    Examples:
      | due_date     | trade_date   | extension_date | err_count |
      | "2020-09-11" | "2020-08-11" | "2020-10-11"   | 0         |
      | "2020-09-11" | "2020-09-12" | "2020-10-11"   | 1         |