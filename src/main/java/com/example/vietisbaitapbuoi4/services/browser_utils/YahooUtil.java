package com.example.vietisbaitapbuoi4.services.browser_utils;

import com.example.vietisbaitapbuoi4.DAO.entities.DTOs.SuggestionResponseDTO;
import com.example.vietisbaitapbuoi4.DAO.entities.KeyWord;
import com.example.vietisbaitapbuoi4.DAO.entities.SearchRecord;
import com.example.vietisbaitapbuoi4.DAO.entities.enums.FOUDATION;
import com.example.vietisbaitapbuoi4.DAO.repositories.KeyWordRepository;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.vietisbaitapbuoi4.services.browser_utils.CaptureScreenUtil.captureScreenshot;

@Slf4j
@Service
public class YahooUtil {
    @Autowired
    private KeyWordRepository keyWordRepository;

    private WebDriver driver;

    public YahooUtil() {
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
                SuggestionResponseDTO searchResponse = searchYahooSuggestions(keyWord.getName());
                List<String> suggestions = searchResponse.getSuggestions();
                String imgPath = searchResponse.getImgPath();
                log.info("Yahoo Suggestions found: " + suggestions.size());
                SearchRecord record = new SearchRecord();
                record.setSearchDate(new Date());
                record.setFoudation(FOUDATION.YAHOO);
                record.setSuggestions(suggestions);
                record.setImgPath(imgPath);
                record.setKeyWord(keyWord);
                keyWord.getRecords().add(record);
                keyWordRepository.save(keyWord);
            }
        }
    }

    private SuggestionResponseDTO searchYahooSuggestions(String keyword) {
        // Extract text from each suggestion
        List<String> suggestionContents = new ArrayList<>();

        // Open Yahoo and search for suggestions
        driver.get("https://www.yahoo.com");

        // Locate the search box and send the search keyword
        WebElement searchBox = driver.findElement(By.name("p"));  // Yahoo uses 'p' for the search query parameter
        searchBox.sendKeys(keyword);

        // Wait for suggestions to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(400));

        // Wait for the specific <ul> with role='listbox' and matching class "_yb_n7mdul"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul._yb_n7mdul[role='listbox']")));

        // Locate the container for suggestions
        WebElement suggestionBox = driver.findElement(By.cssSelector("ul._yb_n7mdul[role='listbox']"));

        // Find all the suggestion elements inside the <li> tags that have role='option'
        List<WebElement> suggestionsList = suggestionBox.findElements(By.cssSelector("li[role='option']"));

        for (WebElement element : suggestionsList) {
            // Get the text within the <span> elements that contain the suggestion text
            WebElement suggestionSpan = element.findElement(By.cssSelector("span._yb_118r9j2"));
            String suggestionText = suggestionSpan.getText();

            if (!suggestionText.isEmpty()) {
                log.info(suggestionText);
                suggestionContents.add(suggestionText);
            }
        }

        String imgPath = captureScreenshot("yahoo",keyword,driver);

        return new SuggestionResponseDTO(suggestionContents, imgPath);
    }

}
