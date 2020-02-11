package com.demo.scripts.api.degiro;

import com.jayway.jsonpath.JsonPath;
import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.Environments.*;
import static com.demo.properties.TestData.*;
import static com.demo.utilities.FileUtility.createLogFile;
import static com.demo.utilities.FileUtility.getFormattedJson;
import static com.demo.utilities.web_services.HttpClientConfig.*;
import static com.demo.utilities.web_services.HttpClientUtils.get;
import static com.demo.utilities.web_services.HttpClientUtils.getClosableHttpClientResponseDetails;

public class ClientInfo {


    private static String host;
    private static String path;
    private static String scheme;

    static final Logger LOG = LogManager.getLogger(ClientInfo.class);

    private static void report() throws Exception {
        String testName = "Get Currency Rates";
        String testDescription = "The purpose of this test is to verify that the correct user details are received.";
        String testCategory = "API";

        startTestReport(testName, testDescription, testCategory);
    }


    public static void getClientInfo(String fileName) throws Exception {
        report();

        scheme = "https";
        host   = INTERNAL_HOST;
        path   = INTERNAL_CLIENT_INFO;

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .addParameter("sessionId", sessionID)
                .build();
        
        response = closeableHttpClient().execute(get(url));
        getClosableHttpClientResponseDetails(response);


        test.info("<pre>"
                + "<br/>"
                + "<center><b>* * * * * * * *    R E Q U E S T    * * * * * * * *</b></center>"
                + "<br />"
                + "<br />"
                + "Method:   " + requestMethod
                + "<br />"
                + "Host:     " + scheme + "://" + host
                + "<br />"
                + "Path:     " + path + "/" + url.getQuery()
                + "<br/>"
                + "<br/>"
                + "</pre>");

        createLogFile(fileName, getFormattedJson(responseBody));
        Assert.assertTrue(responseCode > 199 && responseCode < 300);

        try {
            accountId     = JsonPath.read(responseStringEntity, "$.data.id");
            intAccount    = JsonPath.read(responseStringEntity, "$.data.intAccount");
            bankAccountId = JsonPath.read(responseStringEntity, "$.data.bankAccount.bankAccountId");
            bankBic       = JsonPath.read(responseStringEntity, "$.data.bankAccount.bic");
            bankIban      = JsonPath.read(responseStringEntity, "$.data.bankAccount.iban");
            bankStatus    = JsonPath.read(responseStringEntity, "$.data.bankAccount.status");

            test.pass("<pre>"
                    + "<br/>"
                    + "<b>*** USER DETAILS ***</b>"
                    + "<br />"
                    + "User    ID: " + accountId
                    + "<br />"
                    + "Account ID: " + intAccount
                    + "<br/>"
                    + "<br/>"
                    + "<b>*** BANK DETAILS ***</b>"
                    + "<br/>"
                    + "ID:     " + bankAccountId
                    + "<br/>"
                    + "BIC:    " + bankBic
                    + "<br/>"
                    + "IBAN:   " + bankIban
                    + "<br/>"
                    + "Status: " + bankStatus
                    + "<br/>"
                    + "<br/>"
                    + "</pre>");
        } catch (Exception e) {
            e.printStackTrace();
            test.warning("Check the values from the response body");
        }

    }
}