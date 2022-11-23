package tests;

import dataDriven.ExcelSheetLibrary;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import jxl.read.biff.BiffException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Calculator {

    DesiredCapabilities capabilities;
    AndroidDriver driver;

    @BeforeClass
    public void setup() throws MalformedURLException {

        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability("platformName" , "android");
        capabilities.setCapability("appPackage" , "com.google.android.calculator");
        capabilities.setCapability("appActivity" , "com.android.calculator2.Calculator");

        driver = new AndroidDriver<AndroidElement>(new URL("https://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @Test
    public void testCalculator() throws BiffException, IOException {

        ExcelSheetLibrary excel = new ExcelSheetLibrary("lib/DataDrivenLVLUP.xls");

        int i = 1;
        while (i < excel.RowCount()) {

            MobileElement two = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_" + excel.ReadCell(excel.GetCell("First Number"), i));
            two.click();
            MobileElement plus = (MobileElement) driver.findElementById("com.google.android.calculator:id/op_add");
            plus.click();
            MobileElement four = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_" + excel.ReadCell(excel.GetCell("Second Number"), i));
            four.click();
            MobileElement equalTo = (MobileElement) driver.findElementById("com.google.android.calculator:id/eq");
            equalTo.click();
            MobileElement results = (MobileElement) driver.findElementById("com.google.android.calculator:id/result_final");
            assert results.getText().equals(excel.ReadCell(excel.GetCell("Result"), i)) : "Actual result is: " + results.getText() + " did not match expected value: " + excel.ReadCell(excel.GetCell("Result"), i);
            i++;
        }
    }
    @AfterClass
    public void teardown(){
        driver.quit();
    }
}
