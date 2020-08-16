package cucumber.defs;

import static org.hamcrest.CoreMatchers.is;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import java.io.IOException;
import static utils.PropertiesUtil.getProperties;

import org.junit.Assert;

public class Definitions {

    private static final String PATH;
    private static final String APPLICATION_JSON = "application/json";
    private static final String BASE_URL;
    private static final String AUTH_TOKEN;

    private String dueDate = null;
    private String tradeDate = null;
    private String extensionDate = null;
    private Integer initialLibor = 2;
    private Integer extensionLibor = 4;
    private JSONObject responseObj = null;

    static {
        PATH = getProperties().getProperty("path");
        BASE_URL = getProperties().getProperty("baseurl");
        AUTH_TOKEN = getProperties().getProperty("authToken");
    }

    @Given("^Initial Labor is \"([^\"]*)\"$")
    public void initial_Labor_is(Integer arg1) throws Exception {
        initialLibor = arg1;
    }

    @Given("^Extension Labor is \"([^\"]*)\"$")
    public void extension_Labor_is(Integer arg1) throws Exception {
        extensionLibor = arg1;
    }

    @Given("^Trade Date is \"([^\"]*)\"$")
    public void trade_Date_is(String arg1) throws Exception {
        tradeDate = arg1;
    }

    @Given("^Due Date is \"([^\"]*)\"$")
    public void due_Date_is(String arg1) throws Exception {
        dueDate = arg1;
    }

    @Given("^Extension Date is \"([^\"]*)\"$")
    public void extension_Date_is(String arg1) throws Exception {
        extensionDate = arg1;
    }

    @When("^weighted average LIBOR is calculated$")
    public void weighted_average_LIBOR_is_calculated() throws Exception {
        calculateWeightedLabor(APPLICATION_JSON, BASE_URL + PATH, getPayload());
    }


    @When("^weighted average LIBOR is calculated with invalid token$")
    public void weighted_average_LIBOR_is_calculated_with_invalid_token() throws Exception {
        calculateWeightedLaborWithInvalidToken(APPLICATION_JSON, BASE_URL + PATH, getPayload());
    }

    @When("^weighted average LIBOR is calculated with invalid content type$")
    public void weighted_average_LIBOR_is_calculated_with_invalid_content_type() throws Exception {
        calculateWeightedLaborWithInvalidToken("JSON", BASE_URL + PATH, getPayload());
    }

    @Then("^validate the \"([^\"]*)\" with statusCode \"([^\"]*)\"$")
    public void validate_the_with_statusCode(String arg1, String arg2) throws Exception {
        final String statusCode = responseObj.getString("responseCode");
        Assert.assertThat(statusCode, is(arg2));

        switch (statusCode) {
            case "200": Assert.assertThat(responseObj.get("weightedAverageLibor"), is(Double.valueOf(arg1))); break;
            case "400": Assert.assertThat(responseObj.getString("message"), is(arg1)); break;
            default: break;
        }
        Assert.assertThat(responseObj.getString("responseCode"), is(arg2));
    }

    @Then("^request should be rejected$")
    public void request_should_be_rejected() throws Exception {
        final String statusCode = responseObj.getString("responseCode");
        Assert.assertThat(statusCode, is("401"));
    }

    @Then("^request should be unsupported content type$")
    public void request_should_be_unsupported_content_type() throws Exception {
        final String statusCode = responseObj.getString("responseCode");
        Assert.assertThat(statusCode, is("405"));
    }

    protected void calculateWeightedLaborWithInvalidToken(final String contentType, final String path, final String payload) throws IOException {
        HttpPost request = new HttpPost(path);
        request.addHeader("Content-Type", contentType);
        request.addHeader("Authorization", "Bearer xyz");
        request.setEntity(new StringEntity(payload));

        try (final CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final HttpResponse httpResponse =  httpClient.execute(request);
            responseObj = new JSONObject(EntityUtils.toString(httpResponse.getEntity()));
            responseObj.put("responseCode", String.valueOf(httpResponse.getStatusLine().getStatusCode()));
        }
    }


    protected void calculateWeightedLabor(final String contentType, final String path, final String payload) throws IOException {
        HttpPost request = new HttpPost(path);
        request.addHeader("Content-Type", contentType);
        request.addHeader("Authorization", AUTH_TOKEN);
        request.setEntity(new StringEntity(payload));

        try (final CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final HttpResponse httpResponse =  httpClient.execute(request);
            responseObj = new JSONObject(EntityUtils.toString(httpResponse.getEntity()));
            responseObj.put("responseCode", String.valueOf(httpResponse.getStatusLine().getStatusCode()));
        }
    }

    private String getPayload() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("dueDate", dueDate);
        jsonObject.addProperty("tradeDate", tradeDate);
        jsonObject.addProperty("extensionDate", extensionDate);
        jsonObject.addProperty("initialLibor", initialLibor);
        jsonObject.addProperty("extensionLibor", extensionLibor);
        return jsonObject.toString();
    }

}
