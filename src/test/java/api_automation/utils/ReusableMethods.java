package api_automation.utils;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;

import api_automation.RequestBuilder.TokenRequestBuilder;
import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;


import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class ReusableMethods extends TestBase {


    /**
     * @param String userID
     * @param String password
     * @return TokenRequestBuilder
     * @author Elshan R
     * @date 8/29/2020
     */
    public TokenRequestBuilder createTokenRequest(String userID, String password) {
        TokenRequestBuilder reqBody = new TokenRequestBuilder();
        reqBody.setUsernameOrEmailAddress(userID);
        reqBody.setPassword(password);
        return reqBody;

    }


    /**
     * @param String userID
     * @param String password
     * @return String token
     * @author Elshan R
     * @date 8/29/2020
     */
    public String submitTokenRequest(String userID, String password) {

        //create request body
        TokenRequestBuilder reqBody = createTokenRequest(userID, password);

        //submit post call
        Response res = given().
                relaxedHTTPSValidation().
                contentType(ContentType.JSON).
                body(reqBody).
                when().
                post(property.getProperty("TOKEN_URL"));

        res.prettyPrint();
        //convert response to String
        String response = res.asString();
        //retrieve token value form json
        String token = JsonPath.read(response, "$.result.accessToken").toString();
        System.out.println(token);

        return token;
    }


    /**
     * @param String filePath
     * @return String
     * @author Elshan R
     * @date 8/8/2020
     */

    public static String readFile(String filePath) {
        String reqBody = null;
        File myFile = new File(filePath);

        try {
            reqBody = FileUtils.readFileToString(myFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return reqBody;
    }


    /**
     * @param String filePath
     * @param String date
     * @return void
     * @author Elshan R
     * @date 8/11/2020
     */

    public static void writeToFile(String filePath, String StrData) {

        File myFile = new File(filePath);

        try {
            FileUtils.writeStringToFile(myFile, StrData);
        } catch (IOException e) {

            e.printStackTrace();
        }

    }


    /**
     * @param Object data
     * @return String
     * @author Elshan R
     * @date 8/11/2020
     */

    public String convertObjectToString(Object req) {
        String jsonStr = null;
        ObjectMapper mapper = new ObjectMapper();
        try {

            jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(req);

        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }

        return jsonStr;

    }


}
