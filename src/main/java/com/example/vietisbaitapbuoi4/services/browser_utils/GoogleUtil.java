package com.example.vietisbaitapbuoi4.services.browser_utils;

import com.example.vietisbaitapbuoi4.DAO.entities.DTOs.SuggestionResponseDTO;
import com.example.vietisbaitapbuoi4.DAO.entities.KeyWord;
import com.example.vietisbaitapbuoi4.DAO.entities.SearchRecord;
import com.example.vietisbaitapbuoi4.DAO.entities.Suggestion;
import com.example.vietisbaitapbuoi4.DAO.entities.enums.FOUDATION;
import com.example.vietisbaitapbuoi4.DAO.repositories.KeyWordRepository;
import io.github.bonigarcia.wdm.WebDriverManager;
import jakarta.persistence.Entity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.vietisbaitapbuoi4.services.browser_utils.CaptureScreenUtil.captureScreenshot;

@Slf4j
@Service
public class GoogleUtil {

    @Autowired
    private KeyWordRepository keyWordRepository;

    private WebDriver driver;

    public GoogleUtil() {
        WebDriverManager.chromedriver().setup(); // No need to specify path
        driver = new ChromeDriver();
    }

    @Transactional
    public void performDailySearch() {
        List<KeyWord> keyWords = keyWordRepository.findAll();
        if(keyWords.isEmpty()) {
            log.info("No keywords found");
        }
        else{
            for (KeyWord keyWord : keyWords) {
                SuggestionResponseDTO searchResponse = searchGoogleSuggestions(keyWord.getName());
                List<String> suggestions = searchResponse.getSuggestions();
                String imgPath = searchResponse.getImgPath();
                log.info("GG Suggestions found: " + suggestions.size());
                SearchRecord record = new SearchRecord();
                record.setSearchDate(new Date());
                record.setFoudation(FOUDATION.GOOGLE);
                record.setSuggestions(suggestions);
                record.setImgPath(imgPath);
                record.setKeyWord(keyWord);
                keyWord.getRecords().add(record);
                keyWordRepository.save(keyWord);
            }
        }
    }

    private SuggestionResponseDTO searchGoogleSuggestions(String keyword) {
        // Open Google and fetch suggestions
        driver.get("https://www.google.com");

        // Locate the search box
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(keyword);
        // Wait for suggestions to load (use WebDriverWait for better control)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300));
        // Wait for the suggestions to appear (modify the selector as needed)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".erkvQe")));
        // Now locate the container for suggestions
        WebElement boxContainer = driver.findElement(By.cssSelector(".erkvQe"));
        // Locate the suggestion elements
        List<WebElement> selectsList = boxContainer.findElements(By.xpath("//li[@role='presentation']"));

        // Extract text from each suggestion
        List<String> contentsList = new ArrayList<>();
        for (WebElement element : selectsList) {
            if(!element.getText().isEmpty()) {
                log.info(element.getText());
                contentsList.add(element.getText());
            }
        }
        String imgPath = captureScreenshot("google",keyword,driver);

        return new SuggestionResponseDTO(contentsList,imgPath);
    }

}
