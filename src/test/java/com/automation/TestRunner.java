package com.automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class TestRunner {

    private AppiumDriver<MobileElement> driver;

    //TESTTEN ÖNCE INSPECTOR KAPAT

    @Test
    public void TestRunner() throws InterruptedException {
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
            desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "8.0");
//            desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "\\etsy.apk");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
            desiredCapabilities.setCapability("appPackage", "com.android.calculator2");
            desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");  //bu olmadan da calisiyor


//            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20000);

            driver = new AppiumDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
            Thread.sleep(3000);

            //test 2 + 2 = 4

            MobileElement digit2 = driver.findElement(By.id("com.android.calculator2:id/digit_2"));
            MobileElement plus = driver.findElement(MobileBy.AccessibilityId("plus"));                     //dikkat
            MobileElement equals = driver.findElementByAccessibilityId("equals");
            MobileElement result = driver.findElement(By.id("com.android.calculator2:id/result"));

            digit2.click();
            plus.click();
            digit2.click();
            equals.click();

            String resultText = result.getText();
            Assert.assertEquals(resultText, "4");

            //verify 4 * 5 = 20

            MobileElement digit4 = driver.findElement(By.id("com.android.calculator2:id/digit_4"));
            MobileElement digit5 = driver.findElement(By.id("com.android.calculator2:id/digit_5"));
            MobileElement multiply = driver.findElementByAccessibilityId("multiply");
            MobileElement minus = driver.findElementByAccessibilityId("minus");


            digit4.click();
            multiply.click();
            digit5.click();
            equals.click();

            resultText = result.getText();
            Assert.assertEquals(resultText, "20");

//create a method to take mobile element dynamically

            //50 - 35 = 15
            getDigit(5).click();
            getDigit(0).click();
            minus.click();
            getDigit(3).click();
            getDigit(5).click();
            equals.click();

            resultText = result.getText();
            Assert.assertEquals(resultText, "15");

            driver.closeApp();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



    }

    @Test
    public void test2() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "8.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");  //bu olmadan da calisiyor

        desiredCapabilities.setCapability("app", "https://cybertek-appium.s3.amazonaws.com/etsy.apk");
        //            desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "\\etsy.apk");
        //burada yukaridakini de kullanabilirsin

        driver = new AppiumDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
        Thread.sleep(5000);

        MobileElement you = driver.findElement(By.xpath("\t\n" +
                "//android.widget.FrameLayout[@content-desc=\"You tab, 4 of 5\"]/android.view.ViewGroup/android.widget.TextView"));

        MobileElement settings = driver.findElement(By.xpath("//*[@text='Settings']"));              //nerdeki texti kullandigina dikkat
        MobileElement checkmark = driver.findElement(By.id("com.etsy.android:id/settings_checkbox"));

        you.click();
        settings.click();
        checkmark.click();

        driver.closeApp();



    }

    public MobileElement getDigit(int digit){
        return driver.findElement(By.id("com.android.calculator2:id/digit_" + digit));
    }

}
