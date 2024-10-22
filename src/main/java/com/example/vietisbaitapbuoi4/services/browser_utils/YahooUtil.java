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
public class YahooUtil extends BaseSearchService {

    public YahooUtil() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Override
    protected SuggestionResponseDTO searchSuggestions(String keyword) {
        driver.get("https://www.yahoo.com");

        WebElement searchBox = driver.findElement(By.name("p"));
        searchBox.sendKeys(keyword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul._yb_n7mdul[role='listbox']")));

        WebElement suggestionBox = driver.findElement(By.cssSelector("ul._yb_n7mdul[role='listbox']"));
        List<WebElement> suggestionsList = suggestionBox.findElements(By.cssSelector("li[role='option']"));

        List<String> contentsList = new ArrayList<>();
        for (WebElement element : suggestionsList) {
            WebElement suggestionSpan = element.findElement(By.cssSelector("span._yb_118r9j2"));
            String suggestionText = suggestionSpan.getText();

            if (!suggestionText.isEmpty()) {
                log.info(suggestionText);
                contentsList.add(suggestionText);
            }
        }

        String imgPath = captureScreenshot("yahoo", keyword, driver);
        return new SuggestionResponseDTO(contentsList, imgPath);
    }

    @Override
    protected FOUDATION getFoundation() {
        return FOUDATION.YAHOO;
    }
}
