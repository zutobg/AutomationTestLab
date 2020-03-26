package com.demo.scripts.ui.filters;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.demo.config.BasicTestConfig;
import com.demo.objects.General;
import com.demo.objects.LoginPage;
import com.demo.objects.activity.ActivityBasic;
import com.demo.objects.products.LeveragedPage;
import com.demo.objects.products.ProductsBasic;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.FilePaths.screenshots_actual_folder;
import static com.demo.scripts.ui.products_page.leveraged.OpenLaveragedPage.*;
import static com.demo.objects.products.ProductsBasic.*;


public class LaveragesFilters extends BasicTestConfig {

    private static General general = PageFactory.initElements(driver, General.class);
    private static ActivityBasic activityBasic = PageFactory.initElements(driver, ActivityBasic.class);
    private static LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
    private static LeveragedPage leveragedPage = PageFactory.initElements(driver, LeveragedPage.class);
    private static ProductsBasic productsBasic = PageFactory.initElements(driver, ProductsBasic.class);


    private static void report() throws Exception {
        String testName = "<b>Check leverages filters</b>";
        String testCategory = "Frontend";
        String testDescription = "The purpose of this test is to verify that the user orders history is displayed properly." +
                "<br><br><br>* * *  STEPS DESCRIPTION  * * *</b><br><br>" +
                "[1] Check that the login page can be opened and displayed with correct title.<br>" +
                "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }


    public static void leveragesFilters() throws Exception {
        openLaveragesPage();

        report();
        wait = new WebDriverWait(driver, 50);

        wait.until(ExpectedConditions.visibilityOf(productsBasic.filter_1));
        productsBasic.filter_1.click();
        wait.until(ExpectedConditions.visibilityOf(productsBasic.filter_option_1));
        filter_text_1 = productsBasic.filter_option_1.getText();
        productsBasic.filter_option_1.click();
        test.pass("<b>[STEP 1]</b> Short/Long filter was settled to show only <u><i>" + filter_text_1 + "</i></u>");

        wait.until(ExpectedConditions.visibilityOf(productsBasic.filter_2));
        productsBasic.filter_2.click();
        wait.until(ExpectedConditions.visibilityOf(productsBasic.filter_option_1));
        filter_text_2 = productsBasic.filter_option_1.getText();
        productsBasic.filter_option_1.click();
        test.pass("<b>[STEP 2]</b> Stock Markets filter was settled to show only <u><i>" + filter_text_2 + "</i></u>");

        wait.until(ExpectedConditions.visibilityOf(productsBasic.page_table));
        test.pass("<b>[STEP 3]</b> Leveraged products table was opened successfully");

        takeScreenshot(driver, "Leveraged_Product");
        test.pass("<b>LEVERAGED PAGE</b><br>", MediaEntityBuilder.createScreenCaptureFromPath(screenshots_actual_folder + "Leveraged_Product.png").build());
    }
}