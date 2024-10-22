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

    // Phương thức để chụp ảnh màn hình từ trình duyệt và lưu lại với tên tùy chỉnh
    public static String captureScreenshot(String prev, String fileName, WebDriver driver) {

        // Ép kiểu đối tượng driver sang TakesScreenshot để có thể chụp ảnh màn hình
        TakesScreenshot screenshotTaker = (TakesScreenshot) driver;

        // Tạo tên file ảnh với định dạng 'prev_yyyyMMdd_HHmmss_fileName.png'
        fileName = prev + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_" + fileName;

        // Chụp màn hình và lưu dưới dạng file tạm thời
        File screenshot = screenshotTaker.getScreenshotAs(OutputType.FILE);

        try {
            // Xác định đường dẫn nơi ảnh sẽ được lưu
            String filePath = System.getProperty("user.dir") + "/src/main/resources/static/" + fileName + ".png";
            File destinationFile = new File(filePath);

            // Sao chép ảnh chụp từ file tạm thời đến vị trí lưu đích
            FileUtils.copyFile(screenshot, destinationFile);

            // Ghi log đường dẫn lưu ảnh
            log.info("Screenshot saved: " + filePath);
            return fileName + ".png";
        } catch (IOException e) {
            // Ghi log lỗi nếu quá trình lưu ảnh không thành công
            log.error("Error capturing screenshot", e);
        }

        return null;
    }
}

