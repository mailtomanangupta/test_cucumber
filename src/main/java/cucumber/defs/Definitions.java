package cucumber.defs;

import static java.time.format.DateTimeFormatter.ofPattern;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Definitions {
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    private LocalDate dueDate = null;
    private LocalDate tradeDate = null;
    private LocalDate extensionDate = null;
    private final AtomicInteger errorCount = new AtomicInteger(0);

    @Given("Due Date {string}")
    public void setDueDate(final String date) {
        dueDate = LocalDate.parse(date, ofPattern(DATE_PATTERN));
    }

    @Given("Trade Date {string}")
    public void setTradeDate(final String date) {
        tradeDate = LocalDate.parse(date, ofPattern(DATE_PATTERN));
    }

    @Given("Extension Date {string}")
    public void setExtensionDate(final String date) {
        extensionDate = LocalDate.parse(date, ofPattern(DATE_PATTERN));
    }

    @When("dates are checked")
    public void validate() {
        if (dueDate.isBefore(tradeDate)) {
            errorCount.getAndIncrement();
        }
    }

    @Then("error count should be {int}")
    public void assertResult(final Integer count) {
        assertThat(errorCount.get(), is(count));
    }
}
