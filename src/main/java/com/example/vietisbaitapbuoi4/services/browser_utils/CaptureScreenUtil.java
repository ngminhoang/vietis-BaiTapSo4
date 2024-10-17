package com.example.vietisbaitapbuoi4.services.browser_utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class CaptureScreenUtil {
    public static String captureScreenshot(String prev, String fileName, WebDriver driver) {
        // Cast driver object to TakesScreenshot
        TakesScreenshot screenshotTaker = (TakesScreenshot) driver;

        fileName = prev + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_" + fileName;
        // Take a screenshot and store it as a file
        File screenshot = screenshotTaker.getScreenshotAs(OutputType.FILE);

        try {
            // Specify the path where the screenshot will be saved
            String filePath = System.getProperty("user.dir") + "/src/main/resources/static/" + fileName + ".png";
            File destinationFile = new File(filePath);

            // Copy the screenshot to the destination
            FileUtils.copyFile(screenshot, destinationFile);
            log.info("Screenshot saved: " + filePath);
            return fileName + ".png";
        } catch (IOException e) {
            log.error("Error capturing screenshot", e);
        }
        return null;
    }
}
