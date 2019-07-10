package com.test.TC_02_Example_HttpClient.Fly;

import com.aventstack.extentreports.AnalysisStrategy;
import com.jayway.jsonpath.JsonPath;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.*;
import static com.test.TC_02_Example_HttpClient.Fly.ID_01_AccessToken.accessToken;

public class ID_06_UserInfo extends BasicSetup {

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[ID_06] User Info", "DESCRIPTION");
        test.assignAuthor("Pavel Popov");
        test.assignCategory("POST");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void getUserObject(Method testMethod) throws Exception {

        url = new URIBuilder()
                .setScheme("https")
                .setHost("api.flypaythis.com")
                .setPath("/v2/auth/login")
                .addParameter("accessToken", accessToken)
                .build();

        JSONObject jsonPostData = new JSONObject();
        jsonPostData.put("email", "user@email.com");
        jsonPostData.put("challenge", 1234);
        jsonPostData.put("credentialsType", "email");
        jsonPostData.put("challengeType", "pin");
        jsonPostData.put("countryCodeId", 221);
        jsonPostData.put("phoneNumber", 777799900);

        String requestData = jsonPostData.toString(4);
        String fileName = testMethod.getName() + ".json";

        httpPost(fileName, url, jsonPostData);

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

            String response = jsonObjectResponse.toString();
            String name = JsonPath.read(response, "$.user.profile.name");
            Assert.assertEquals(name, "Automation");
    }
}
