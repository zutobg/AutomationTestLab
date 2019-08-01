package com.test.uat.TC_01_Bet;

import com.aventstack.extentreports.AnalysisStrategy;
import com.jayway.jsonpath.JsonPath;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.lang.reflect.Method;

import static com.constants.API.get_sports_tree;
import static com.setup.ConsoleRunner.host;
import static com.setup.ConsoleRunner.scheme;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.url;
import static com.setup.OkHttpClientUtils.*;
import static com.test.uat.TC_01_Bet.ID_01_LogIn.site;
import static com.test.uat.TC_01_Bet.ID_02_ExternalLogin.sessionToken;


public class ID_08_GetSportsTree extends BasicSetup {



    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[ID_08] Get Sports Tree", "DESCRIPTION");
        test.assignAuthor("YOUR NAME");
        test.assignCategory("OkHttpClient");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void getSportsTree(Method testMethod) throws Exception {

        String fileName = testMethod.getName() + ".json";

        RequestBody requestBody = new FormBody.Builder()
                .add("sessionToken", sessionToken)
                .add("sport", "SOCCER")
                .add("includeOutrights", "true")
                .add("includeEvents", "true")
                .add("locale", "en-gb")
                .add("siteId", String.valueOf(site))
                .add("channelId", "6")
                .build();

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(get_sports_tree)
                .build();

        request = new Request.Builder()
                .url(url.toURL())
                .post(requestBody)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Origin", "https://sports.uat.pyr")
                .build();

        okClientRequest(fileName, request);


        test.info("<pre>"
                + "[ REQUEST  HEADERS ]"
                + "<br />"
                + "<br />"
                + "Method:   " + requestMethod
                + "<br />"
                + "Scheme:   " + requestURLScheme.toUpperCase()
                + "<br />"
                + "Host:     " + requestURLHost
                + "<br />"
                + "Path:     " + requestURLPath
                + "<br />"
                + "<br />"
                + getRequestOkClientHeaders()
                + "<br />"
                + "<br />"
                + "[ REQUEST  BODY ]"
                + "<br />"
                + "<br />"
                + requestBodyToString(requestBody).replaceAll("&", "\n").replaceAll("\"", "")
                + "<br />"
                + "<br />"
                + "</pre>");

        Object object = parser.parse(new FileReader(filePath + "/" + "report/JSON/" + fileName));
        JSONObject jsonResponse = (JSONObject) object;

        Long competition_1 = JsonPath.read(jsonResponse,"popularCompetitions[0].numEvents");
        if (competition_1 > 0) {
            boolean displayed       = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].displayed");
            String eventState      = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].state");
            boolean eventIsInplay   = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].isInplay");
            Long eventId         = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].id");
            String competitionName = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].compNames.longName");
            String eventNames      = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].names.longName");

            test.pass("<pre>"
                    + "[ KEYS ]"
                    + "<br />"
                    + "Event Names  = "      + eventNames
                    + "<br />"
                    + "Competition Name = "  + competitionName
                    + "<br />"
                    + "Event ID = "          + eventId
                    + "<br />"
                    + "Event IsInplay = "    + eventIsInplay
                    + "<br />"
                    + "Event State = "       + eventState
                    + "<br />"
                    + "Event IsDisplayed = " + displayed
                    + "</pre>");
        }

    }
}

