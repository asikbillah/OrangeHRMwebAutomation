package org.example.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.example.baseDriver.PageDriver;
import org.example.utilitis.GetScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;

public class LoginPage {
    ExtentTest test;
     public LoginPage(ExtentTest test){
         PageFactory.initElements(PageDriver.getCurrentDriver(),this);
         this.test = test;
     }
     @FindBy(xpath = "//input[@placeholder='Username']")
    WebElement username;
    @FindBy(xpath = "//input[@placeholder='Password']")
     WebElement password;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    WebElement loginButton;

    public void failCase(String message, String scName) throws IOException {
        test.fail("<p style=\"color:#FF5353; font-size:13px\"><b>"+message+"</b></p>");
        Throwable t = new InterruptedException("Exception");
        test.fail(t);
        @SuppressWarnings("unused")
        String screenShotPath = GetScreenshot.capture(PageDriver.getCurrentDriver(), ""+scName+"");
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + ""+scName+".png";
        test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
        Assert.assertTrue(username.isDisplayed());
        PageDriver.getCurrentDriver().quit();
    }

    public void passCase(String message){
        test.pass("<p style=\"color:#FF5353; font-size:13px\"><b>"+message+"</b></p>");
    }

    @SuppressWarnings("unused")
	public void passCaseWithSC(String message, String scName) throws IOException {
        test.pass("<p style=\"color:#FF5353; font-size:13px\"><b>"+message+"</b></p>");
        String screenShotPath = GetScreenshot.capture(PageDriver.getCurrentDriver(), ""+scName+"");
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + ""+scName+".png";
        test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
    }

    public void login() throws InterruptedException, IOException {
        try {
            test.info("Please Enter username");
            if(username.isDisplayed()) {
                username.sendKeys("Admin");
                passCase("username entered");

                try {
                    test.info("Please Enter password");
                    if(password.isDisplayed()) {
                        password.sendKeys("admin123");
                        passCase("password send");

                        try {
                            test.info("Click on the LogIn Button");
                            if(loginButton.isDisplayed()) {
                                loginButton.click();
                                Thread.sleep(8000);
                                passCaseWithSC("Login Successful","loginpass");
                            }
                        } catch (Exception e) {
                            failCase("Login button was not locatable", "loginbuttonfail");
                        }
                    }
                } catch (Exception e) {
                    failCase("password was not locatable", "password fail");
                }
            }
        } catch (Exception e) {
            failCase("Username was not locatable", "username fail");
        }


    }

}
