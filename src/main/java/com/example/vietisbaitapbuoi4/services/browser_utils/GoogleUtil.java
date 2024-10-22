package com.example.vietisbaitapbuoi4.services.browser_utils;

import com.example.vietisbaitapbuoi4.DAO.entities.DTOs.SuggestionResponseDTO;
import com.example.vietisbaitapbuoi4.DAO.entities.enums.FOUDATION;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.example.vietisbaitapbuoi4.services.browser_utils.CaptureScreenUtil.captureScreenshot;

@Slf4j
@Service
public class GoogleUtil extends BaseSearchService {

    public GoogleUtil() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Override
    protected SuggestionResponseDTO searchSuggestions(String keyword) {
        driver.get("https://www.google.com");

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(keyword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".erkvQe")));

        WebElement boxContainer = driver.findElement(By.cssSelector(".erkvQe"));
        List<WebElement> selectsList = boxContainer.findElements(By.xpath("//li[@role='presentation']"));

        List<String> contentsList = new ArrayList<>();
        for (WebElement element : selectsList) {
            if (!element.getText().isEmpty()) {
                log.info(element.getText());
                contentsList.add(element.getText());
            }
        }

        String imgPath = captureScreenshot("google", keyword, driver);
        return new SuggestionResponseDTO(contentsList, imgPath);
    }

    @Override
    protected FOUDATION getFoundation() {
        return FOUDATION.GOOGLE;
    }
}
