$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("list.feature");
formatter.feature({
  "name": "Date Validation",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Validate Dates",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Due Date \u003cdue_date\u003e",
  "keyword": "Given "
});
formatter.step({
  "name": "Trade Date \u003ctrade_date\u003e",
  "keyword": "And "
});
formatter.step({
  "name": "Extension Date \u003cextension_date\u003e",
  "keyword": "And "
});
formatter.step({
  "name": "dates are checked",
  "keyword": "When "
});
formatter.step({
  "name": "error count should be \u003cerr_count\u003e",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "due_date",
        "trade_date",
        "extension_date",
        "err_count"
      ]
    },
    {
      "cells": [
        "\"2020-09-11\"",
        "\"2020-08-11\"",
        "\"2020-10-11\"",
        "0"
      ]
    },
    {
      "cells": [
        "\"2020-09-11\"",
        "\"2020-09-12\"",
        "\"2020-10-11\"",
        "1"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Validate Dates",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Due Date \"2020-09-11\"",
  "keyword": "Given "
});
formatter.match({
  "location": "Definitions.setDueDate(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Trade Date \"2020-08-11\"",
  "keyword": "And "
});
formatter.match({
  "location": "Definitions.setTradeDate(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Extension Date \"2020-10-11\"",
  "keyword": "And "
});
formatter.match({
  "location": "Definitions.setExtensionDate(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "dates are checked",
  "keyword": "When "
});
formatter.match({
  "location": "Definitions.validate()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "error count should be 0",
  "keyword": "Then "
});
formatter.match({
  "location": "Definitions.assertResult(Integer)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Validate Dates",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Due Date \"2020-09-11\"",
  "keyword": "Given "
});
formatter.match({
  "location": "Definitions.setDueDate(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Trade Date \"2020-09-12\"",
  "keyword": "And "
});
formatter.match({
  "location": "Definitions.setTradeDate(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Extension Date \"2020-10-11\"",
  "keyword": "And "
});
formatter.match({
  "location": "Definitions.setExtensionDate(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "dates are checked",
  "keyword": "When "
});
formatter.match({
  "location": "Definitions.validate()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "error count should be 1",
  "keyword": "Then "
});
formatter.match({
  "location": "Definitions.assertResult(Integer)"
});
formatter.result({
  "status": "passed"
});
});