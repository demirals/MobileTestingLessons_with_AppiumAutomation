package com.cybertek.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Day1 {

    AppiumDriver<MobileElement> driver;

    @Test
    public void test1() throws InterruptedException, MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "8.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
        //if it is real device, we need to pass UUID parameter for DEVICE_NAME
        //terminalde #adb devices   ile deviceleri siralarsin

        //either you specify app>> //path/to/app.apk
        //or if app is already installed, you need to specify appActivity and appPackage
        //this info you can find in internet, at work >> ask to developer

        //set yours applications package name
        desiredCapabilities.setCapability("appPackage", "com.android.calculator2");

        //set your applications MainActivity i.e. LauncherActivity name
        desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

        //http://localhost:4723/wd/hub >>  address of the appium server, If you have appium server on the same
        //computer. just use localhost
        //4723 >> default port number
        //we need to provide 2 parameters : URL of appium and desired capabilities
        driver = new AppiumDriver<>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);

        Thread.sleep(3000);

        driver.closeApp();







    }




}
