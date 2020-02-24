package com.demo.scripts.ui.degiro;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.demo.config.BasicTestConfig;
import com.demo.objects.Degiro.ActivityOrdersPage;
import com.demo.objects.Degiro.General;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.FilePaths.screenshots_actual_folder;
import static com.demo.utilities.user_interface.ElementScreenshot.*;
import static com.demo.utilities.user_interface.HandleTable.*;


public class ActivityOrdersHistory extends BasicTestConfig {

    private static General general = PageFactory.initElements(driver, General.class);
    private static ActivityOrdersPage activityOrdersPage = PageFactory.initElements(driver, ActivityOrdersPage.class);


    private static void report() throws Exception {
        String testName        = "<b>[WEB] User Orders History</b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the user orders history is displayed properly."              +
                                 "<br><br><b>*** STEPS DESCRIPTION ***</b><br><br>"                                                       +
                                 "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                                 "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                                 "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }



    public static void checkUserOrdersHistory() throws Exception {
        report();
        wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOf(general.quick_search_input));
        general.side_navigation_activity_btn.click();

        wait.until(ExpectedConditions.visibilityOf(general.account_content_available_to_spend));
        activityOrdersPage.orders_hitory_btn.click();

        wait.until(ExpectedConditions.visibilityOf(activityOrdersPage.orders_history_table));
        elementScreenshot(activityOrdersPage.orders_history_table, "OrdersHistory_Actual");

        test.pass("<b>ORDERS HISTORY</b><br>", MediaEntityBuilder.createScreenCaptureFromPath(screenshots_actual_folder + "OrdersHistory_Actual" + ".png").build());

        handleWebTable(activityOrdersPage.orders_history_table);
    }
}