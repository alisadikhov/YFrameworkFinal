package api_automation.stepDefinition;


import api_automation.RequestBuilder.ExpenseBuilder;
import api_automation.utils.ReusableMethods;
import com.jayway.jsonpath.JsonPath;


import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

import java.util.List;

public class GenericMealBAPIsteps extends ReusableMethods {
    private String flag;
    private String token;
    private static Response response;
    private static String stringResponse;
    Scenario scenario;
    String requestBody;
    String recordID;


    /**
     * this step keeps scenario alive, so we can validate get response once and
     * validate it in multiple scenarios with scenario outline
     **/
    @Before
    public void keepScenario(Scenario scenario) {

        this.scenario = scenario;
    }


    @Given("User gets token for {string} account when flag is {string}")
    public void user_gets_token_for_account_when_flag_is(String accountType, String flag) {
        this.flag = flag;

        // this code will run if flag is true
        if (flag.equalsIgnoreCase("true")) {

            //we get user id and password form config file
            String userID = null;
            String password = null;

            if (accountType.equalsIgnoreCase("company")) {
                //retrieve company userID and password from config file
                userID = property.getProperty("CompanyUserID");
                password = property.getProperty("CompanyPassword");

            } else if (accountType.equalsIgnoreCase("employee")) {
                //retrieve employee userID and password from config file
                userID = property.getProperty("EmployeeUserID");
                password = property.getProperty("EmployeePassword");

            } else {

                System.out.println("ACCOUNT TYPE IS WRONG !!!!!! ");
            }

            //validate userID and Password is not null
            Assert.assertNotNull(userID);
            Assert.assertNotNull(password);

            //write userID and password to report
            scenario.write("UserID: " + userID);
            scenario.write("Password: " + password);

            // After we get userID and Password we submit token request
            token = submitTokenRequest(userID, password);

            //write token to report
            scenario.write("Token: " + token);

        }

    }

    @When("User submit GET request to {string}")
    public void user_submit_GET_request_to(String url) {
        //code will run when flag is true
        if (flag.equalsIgnoreCase("true")) {

            //get url from config file
            String URLName = property.getProperty(url);
            // submit Get request to that URL
            response = given().
                    header("Authorization", "Bearer " + token).
                    when().
                    get(URLName);
            //print response
            response.prettyPrint();
            //convert response to string
            stringResponse = response.asString();
            //write response to report
            scenario.write("Response: " + stringResponse);
            //attach reponse to report
            //stringResponse.getBytes() converts string to byte array.
            scenario.embed(stringResponse.getBytes(), "application/json");

        }


    }

    @When("User validates if the status code is {int}")
    public void user_validates_if_the_status_code_is(int statusCode) {

        if (flag.equalsIgnoreCase("true")) {
            //write statusCode to report
            scenario.write("Staus code: " + response.statusCode());
            //validate status code of response
            Assert.assertEquals(statusCode, response.statusCode());
        }
    }

    @Then("User validates {string} in response")
    public void user_validates_in_response(String element) {
        Assert.assertTrue("Element cannot found !!!!", stringResponse.contains(element));

    }


    @Then("Create request body {string} , {string}, {string},{string} for expense API")
    public void create_request_body_for_expense_API(String name, String giftRecipient, String expenseDateTime, String amount) {
        if (flag.equalsIgnoreCase("true")) {
            //create expense
            ExpenseBuilder expense = new ExpenseBuilder();
            expense.setName(name);
            expense.setGiftRecipient(giftRecipient);
            expense.setExpenseDateTime(expenseDateTime);
            expense.setAmount(amount);
            //convert expense object to string
            requestBody = convertObjectToString(expense);
            scenario.write("requestData: " + requestBody);
        }
    }

    @Then("User submits POST request to {string} api")
    public void user_submits_POST_request_to_api(String url) {
        if (flag.equalsIgnoreCase("true")) {
            response = given().
                    relaxedHTTPSValidation().
                    header("Authorization", "Bearer " + token).
                    contentType(ContentType.JSON).
                    body(requestBody).
                    when().
                    post(property.getProperty(url));
            //will print response and convert to string
            stringResponse = response.prettyPrint();
            scenario.write("responseData: " + stringResponse);
        }
    }


    @When("User retrieves {string} from response")
    public void user_retrieves_from_response(String element) {
        if (flag.equalsIgnoreCase("true")) {
            //retrieve record ID from response
            if (element.equalsIgnoreCase("ID")) {
                recordID = JsonPath.read(stringResponse, "$.result.id").toString();
            }

            scenario.write("ID: " + recordID);
        }
    }


    @Then("User submits request to delete expense with record ID")
    public void user_submits_request_to_delete_expense_with_record_ID() {
        if (flag.equalsIgnoreCase("true")) {
            //submit Post request to delete record(Add record ID to the end of URL)
            response = given().
                    relaxedHTTPSValidation().
                    header("Authorization", "Bearer " + token).
                    contentType(ContentType.JSON).
                    when().
                    post(property.getProperty("Delete_URL") + recordID);
            //will print response and convert to string
            stringResponse = response.prettyPrint();
            scenario.write("responseData: " + stringResponse);
        }
    }

    @Then("User validates if the record was deleted")
    public void user_validates_if_the_record_was_deleted() {
        if (flag.equalsIgnoreCase("true")) {
            //retrieve list of all IDs in response
            List<String> IDlist = JsonPath.read(stringResponse, "$.result[*].id");
            scenario.write("List of existing record IDs: " + IDlist);
            //validate IDlist doesn't contain ID of your record
            Assert.assertFalse(IDlist.contains(recordID));
            scenario.write(recordID + " is deleted and doesn't exist anymore");
        }
    }

    @Then("User creates request body {string} , {string}, {string},{string} for expense API")
    public void user_creates_request_body_for_expense_API(String name, String giftRecip, String dateTime, String amount) {
        if (flag.equalsIgnoreCase("true")) {
            //create expense
            ExpenseBuilder expense = new ExpenseBuilder();
            expense.setName(name);
            expense.setGiftRecipient(giftRecip);
            expense.setExpenseDateTime(dateTime);
            expense.setAmount(amount);
            //convert expense object to string
            requestBody = convertObjectToString(expense);
            scenario.write("requestData: " + requestBody);
        }
    }

    @Then("User validates value of {string} , {string}, {string},{string} in response")
    public void user_validates_value_of_in_response(String name, String recip, String time, String amount) {
        if (flag.equalsIgnoreCase("true")) {
            String nameOutput = JsonPath.read(stringResponse, "$.result.name").toString();
            String timeOutput = JsonPath.read(stringResponse, "$.result.expenseDateTime").toString();
            String giftRecOutput = JsonPath.read(stringResponse, "$.result.giftRecipient").toString();
            String amountOutput = JsonPath.read(stringResponse, "$.result.amount").toString();
            Assert.assertEquals(name, nameOutput);
            Assert.assertEquals(recip, giftRecOutput);
            Assert.assertEquals(time, timeOutput);
            Assert.assertEquals(amount, amountOutput);
        }
    }


}
