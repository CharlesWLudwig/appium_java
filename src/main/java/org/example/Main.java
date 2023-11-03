package org.example;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) throws MalformedURLException {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("appPackage", "com.android.settings");
        capabilities.setCapability("appActivity", "com.android.settings.Settings");

        AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/"), capabilities);

        driver.activateApp("com.android.settings");

        // Scroll down to the bottom of the screen and find the 'About Phone' menu option
        WebElement aboutPhone = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"About phone\"));");
        // Enter the 'About phone' menu option by clicking the element
        aboutPhone.click();

        // Scroll down to the bottom of the screen and find the 'Build number' menu option
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Build number\"));");

        // Print the element corresponding to the Build Number text (without foreknowledge of the Build Number itself)
        WebElement element = driver.findElementByXPath("(//*[contains(@resource-id, 'android:id/summary')])[8]");
        String buildNumber = element.getAttribute("name");
        System.out.println("The Build Number is: " + buildNumber);

        // Compare the Capabilities version number with the number in the System Settings (to avoid hard-coding the Software version)
        String devicePlatformVersion = driver.getCapabilities().getCapability("platformVersion").toString();
        String androidVersionText = String.format("(//*[contains(@text,'%s')])",devicePlatformVersion);
        WebElement androidVersionNumber = driver.findElement(By.xpath(androidVersionText));
        String androidNumber = androidVersionNumber.getAttribute("name");
        System.out.println("The Android Platform Version is: " + androidNumber);
    };
};
