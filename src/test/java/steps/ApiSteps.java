package steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApiSteps {

    private Response response;
    private String endpoint;

    @Given("the API endpoint is {string}")
    public void theApiEndpointIs(String url) {
        this.endpoint = url;
    }

    @When("I send a GET request")
    public void iSendAGETRequest() {
        response = given()
                .header("x-api-key", "reqres-free-v1") // pakai API key
                .when()
                .get(endpoint);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatus) {
        assertEquals(expectedStatus, response.getStatusCode());
    }

    @Then("the response should contain {string}")
    public void theResponseShouldContain(String expectedValue) {
        String body = response.getBody().asString();
        assertTrue("Response body does not contain expected value: " + expectedValue,
                body.contains(expectedValue));
    }
}
