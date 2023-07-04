package api_automation.stepDefinition;

import static io.restassured.RestAssured.given;

import api_automation.RequestBuilder.TokenRequestBuilder;
import org.junit.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;


import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class TokenAPI {
    String strRequest;
    Response response;

    @Given("User creates request data with {string} and {string}")
    public void user_creates_request_data_with_and(String userID, String password) throws JsonProcessingException {
        //create request body
        TokenRequestBuilder body = new TokenRequestBuilder();
        body.setUsernameOrEmailAddress(userID);
        body.setPassword(password);
        //convert request data to string
        ObjectMapper objMap = new ObjectMapper();
        strRequest = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(body);

        System.out.println(strRequest);

    }

    @Given("User submits request to TOKEN api")
    public void user_submits_request_to_TOKEN_api() {

        //submit post call
        response = given().
                relaxedHTTPSValidation().
                contentType(ContentType.JSON).
                body(strRequest).
                when().
                post("https://34.235.0.4/api/tokenauth/authenticate");

        //print and convert String
        response.prettyPrint();


    }

    @Given("User validates if status code is {int}")
    public void user_validates_if_status_code_is(int statusCode) {

        Assert.assertEquals(statusCode, response.statusCode());

    }

    @Then("User retrieves access token from response")
    public void user_retrieves_access_token_from_response() {

        String token = JsonPath.read(response.asString(), "$.result.accessToken").toString();

        System.out.println(token);

        Assert.assertNotNull(token);

    }


    @Then("User valitates {string}")
    public void user_valitates(String errorMsj) {

        String strResponse = response.asString();

        Assert.assertTrue("ErrorMessage was not found !!! ", strResponse.contains(errorMsj));


    }


}
