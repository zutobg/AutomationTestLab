package com.test.examples.TC_02_Example_HttpClient.Fly;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.*;
import static com.test.examples.TC_02_Example_HttpClient.Fly.ID_01_AccessToken.accessToken;

public class ID_03_Authenticate extends BasicSetup {

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[ID_03] Authenticate User", "The purpose of this test is to verify that every account can be authorized successfully.");
        test.assignAuthor("Pavel Popov");
        test.assignCategory("POST");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void authentication(Method testMethod) throws Exception {

        /*** Set URL address components. ***/
        url = new URIBuilder()
                .setScheme("https")
                .setHost("api.flypaythis.com")
                .setPath("/v2/auth/login")
                .addParameter("accessToken", accessToken)
                .build();

        /*** Create JSON object for request body. ***/
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "user@email.com");
        jsonObject.put("challenge", 1234);
        jsonObject.put("credentialsType", "email");
        jsonObject.put("challengeType", "pin");
        jsonObject.put("countryCodeId", 221);
        jsonObject.put("phoneNumber", 777799900);

        String requestData = jsonObject.toString(4);
        String fileName = testMethod.getName() + ".json";

        StringEntity entity = new StringEntity(jsonObject.toString());

        /*** Send request by using method 'httpPost' from HttpClientUtils.class ***/
        httpPost(fileName, url, entity);


        /*** Add request properties to the report. ***/
        test.info("<pre>"
                + "[ REQUEST  HEADERS ]"
                + "<br />"
                + "<br />"
                + "Method:  "  + requestMethod   +   "  "   + requestProtocol
                + "<br />"
                + "Scheme:  "    + requestScheme.toUpperCase()
                + "<br />"
                + "Host:    "      + requestHost
                + "<br />"
                + "Path:    "      + requestPath
                + "<br />"
                + "\n"
                + getPostRequestHeaders().replace(", ", "\n")
                + "<br />"
                + "<br />"
                + "<br />"
                + "[ REQUEST  BODY ]"
                + "<br />"
                + "<br />"
                + requestData.replace("    ", "&nbsp;&nbsp;")
                + "<br />"
                + "<br />"
                + "</pre>");
    }
}
