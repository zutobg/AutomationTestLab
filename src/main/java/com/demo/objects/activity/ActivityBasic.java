package com.demo.objects.activity;

import com.demo.config.BasicTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ActivityBasic extends BasicTestConfig {

    public static final Logger LOG = LogManager.getLogger(ActivityBasic.class);
    public ActivityBasic(WebDriver driver) {
        this.driver = driver;
    }
    final WebDriver driver;


    //  *****   Tab Menu Panel
    @FindBy(how = How.CSS, using = "div[role=tabpanel]")
    public WebElement tab_menu;

    @FindBy(how = How.CSS,using = "a[href=#/orders/open]")
    public WebElement tab_menu_orders_btn;

    @FindBy(how = How.CSS, using = "a[href=#/transactions]")
    public WebElement activity_tab_menu_transactions_btn;





    //  *****   Orders
    @FindBy(how = How.CSS, using = "button[data-id='/orders/history']")
    public WebElement orders_hitory_btn;

    @FindBy(how = How.CSS, using = "table._3QuQRAgUeF2pqrF7IRP6nC")
    public WebElement orders_history_table;

    @FindBy(how = How.CSS, using = "span[data-id=96826][data-name=productName]")
    public WebElement orders_history_table_row1_product;

    @FindBy(how = How.CSS, using = "time[data-id=ecbadb6e-90d6-4db0-8dc2-62d6ae1a1fbd][data-field=createdAt]")
    public WebElement orders_history_table_row1_time;

    @FindBy(how = How.CSS, using = "span[data-name=exchangeAbbr]")
    public WebElement orders_history_table_row1_excahnge;
}
