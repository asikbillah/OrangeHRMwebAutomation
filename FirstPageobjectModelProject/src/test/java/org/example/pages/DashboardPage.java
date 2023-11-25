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

public class DashboardPage {
    ExtentTest test;
     public DashboardPage(ExtentTest test){
         PageFactory.initElements(PageDriver.getCurrentDriver(),this);
         this.test = test;
     }
     @FindBy(xpath = "//span[normalize-space()='Admin']")
    WebElement admin;
    @FindBy(xpath = "/html/body/div/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a")
    WebElement pim;

    public void failCase(String message, String scName) throws IOException {
        test.fail("<p style=\"color:#FF5353; font-size:13px\"><b>"+message+"</b></p>");
        Throwable t = new InterruptedException("Exception");
        test.fail(t);
        @SuppressWarnings("unused")
        String screenShotPath = GetScreenshot.capture(PageDriver.getCurrentDriver(), ""+scName+"");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + ""+scName+".png";
        test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
        Assert.assertTrue(admin.isDisplayed());
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

    public void admin() throws InterruptedException, IOException {
        try{
            test.info("Click on admin");
            if(admin.isDisplayed()){
                admin.click();
                Thread.sleep(5000);
                passCaseWithSC("Clicked","adminpass");

                try{
                    test.info("Click on pim");
                    if(pim.isDisplayed()){
                        pim.click();
                        Thread.sleep(5000);
                        passCaseWithSC("Clicked","pimpass");
                    }
                } catch (Exception e){
                    failCase("PIM was not locatable", "pimfail");
                }

            }
        } catch (Exception e){
            failCase("Admin was not locatable", "adminfail");
        }


    }

}
