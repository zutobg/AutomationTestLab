package com.demo.objects.Degiro;

import com.demo.config.BasicTestConfig;
import com.demo.utilities.user_interface.PageScroll;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasicTestConfig {

    public static final Logger LOG = LogManager.getLogger(LoginPage.class);

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input#username.nmcEvmpQGs-DUsPuI4fzO")
    public static WebElement login_user_input;

    @FindBy(css = "input#password.nmcEvmpQGs-DUsPuI4fzO.swuIwUNQwFAucmifmmWO4")
    public static WebElement login_pass_input;

    @FindBy(how = How.CSS, using = "button._6iSTUGFRm1Pp_z-8_y0Y5.Zte1YssVqY438XSyivt7f")
    public static WebElement login_submit_btn;

    }
