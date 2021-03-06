package com.demo.scripts.ui.products_page;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.demo.config.BasicTestConfig;
import com.demo.objects.General;
import com.demo.objects.products.ProductsBasic;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.FilePaths.screenshots_actual_folder;


public class OpenFirstRowProduct extends BasicTestConfig {

    private static ProductsBasic productsBasic = PageFactory.initElements(driver, ProductsBasic.class);
    private static General general = PageFactory.initElements(driver, General.class);

    private static String product;


    private static void report() throws Exception {
        String testName = "<b>Open product</b>";
        String testCategory = "Frontend";
        String testDescription = "The purpose of this test is to verify that the user orders history is displayed properly." +
                "<br><br><br>* * *  STEPS DESCRIPTION  * * *</b><br><br>" +
                "[1] Check that the login page can be opened and displayed with correct title.<br>" +
                "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }


    public static void openProduct() throws Exception {
        report();
        wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.visibilityOf(productsBasic.table_row1_product));
        product = productsBasic.table_row1_product.getText();
        test.pass("<b>[STEP 1]</b> First search result is <i><u>" + product + "</i></u>");
        productsBasic.table_row1_product.click();
        test.pass("<b>[STEP 2]</b> Product page was opened successfully");
        takeScreenshot(driver, "Product_Details");
        test.pass("<pre><center><b>*** SCREENSHOT ***</b><br><br>", MediaEntityBuilder.createScreenCaptureFromPath(screenshots_actual_folder  + "Product_Details.png", "<br>").build());

        wait.until(ExpectedConditions.visibilityOf(productsBasic.product_back_btn));
        takeScreenshot(driver, "Product");
        productsBasic.product_back_btn.click();
        test.pass("<b>[STEP 3]</b> Product page was closed successfully");

        takeScreenshot(driver, "Product");
        test.pass("<pre><center><b>*** SCREENSHOT ***</b><br><br>", MediaEntityBuilder.createScreenCaptureFromPath(screenshots_actual_folder  + "Product.png", "<br>").build());
    }
}
