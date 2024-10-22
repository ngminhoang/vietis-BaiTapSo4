package com.example.vietisbaitapbuoi4.services.browser_utils;

import com.example.vietisbaitapbuoi4.DAO.entities.DTOs.SuggestionResponseDTO;
import com.example.vietisbaitapbuoi4.DAO.entities.KeyWord;
import com.example.vietisbaitapbuoi4.DAO.entities.SearchRecord;
import com.example.vietisbaitapbuoi4.DAO.entities.enums.FOUDATION;
import com.example.vietisbaitapbuoi4.DAO.repositories.KeyWordRepository;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.vietisbaitapbuoi4.services.browser_utils.CaptureScreenUtil.captureScreenshot;

@Slf4j
@Component
public abstract class BaseSearchService {

    @Autowired
    protected KeyWordRepository keyWordRepository;

    protected WebDriver driver;

    // Abstract method for individual services to implement their own search logic
    protected abstract SuggestionResponseDTO searchSuggestions(String keyword);

    // Abstract method for individual services to specify their foundation (Google/Yahoo)
    protected abstract FOUDATION getFoundation();

    // General method to perform daily search and save results
    @Transactional
    public void performDailySearch() {
        List<KeyWord> keyWords = keyWordRepository.findAll();
        if (keyWords.isEmpty()) {
            log.info("No keywords found");
        } else {
            for (KeyWord keyWord : keyWords) {
                SuggestionResponseDTO searchResponse = searchSuggestions(keyWord.getName());
                List<String> suggestions = searchResponse.getSuggestions();
                String imgPath = searchResponse.getImgPath();
                log.info("{} Suggestions found: {}", getFoundation(), suggestions.size());

                // Save search record
                SearchRecord record = new SearchRecord();
                record.setSearchDate(new Date());
                record.setFoudation(getFoundation());
                record.setSuggestions(suggestions);
                record.setImgPath(imgPath);
                record.setKeyWord(keyWord);
                keyWord.getRecords().add(record);

                keyWordRepository.save(keyWord);
            }
        }
    }
}
