package api_automation.stepDefinition;

import api_automation.RequestBuilder.TokenRequestBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class TimeOut {

    String strRequest;

    @Given("Creates request data with {string} and {string}")
    public void creates_request_data_with_and(String userID, String password) throws JsonProcessingException {
        //create request body
        TokenRequestBuilder body = new TokenRequestBuilder();
        body.setUsernameOrEmailAddress(userID);
        body.setPassword(password);
        //convert request data to string
        ObjectMapper objMap = new ObjectMapper();
        strRequest = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(body);

        System.out.println(strRequest);

    }

    @Given("Submits request to TOKEN api")
    public void submits_request_to_TOKEN_api() {

        //CONNECTION_TIMEOUT --is time to establish connection (request)
        //SO_TIMEOUT (SOCKET timeout)- is waiting time for data after establishing connection  (response)


        RequestSpecBuilder builder = new RequestSpecBuilder();
        // is used to set timeout.   -- 3000 means 3 seconds.
        RestAssuredConfig apiTimeOut = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("CONNECTION_TIMEOUT", 3000)
                        .setParam("SO_TIMEOUT", 3000));
        //another way to create header
        Header header1 = new Header("Centent-Type", "application/json");
        Header header2 = new Header("", "");
        Header header3 = new Header("", "");
        //we need to add all header objects to headers object
        Headers headers = new Headers(header1, header2, header3);

        //add requestBody, timeout , content Type to builder object
        builder.setBody(strRequest);
        builder.setConfig(apiTimeOut);
        builder.setContentType(ContentType.JSON);

        // build RequestSpecBuilder obj and assign it to RequestSpecification obj
        RequestSpecification reqSpec = builder.build();


        Response response = given()
                .relaxedHTTPSValidation()
                .headers(headers)                // headers obj
                .spec(reqSpec)                    // pass request specification
                .when()
                .post("https://34.235.0.4/api/tokenauth/authenticate");

        response.prettyPrint();


    }


}