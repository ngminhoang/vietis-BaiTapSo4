package com.example.vietisbaitapbuoi4.services;

import com.example.vietisbaitapbuoi4.DAO.entities.KeyWord;
import com.example.vietisbaitapbuoi4.DAO.entities.SearchRecord;
import com.example.vietisbaitapbuoi4.DAO.repositories.KeyWordRepository;
import com.example.vietisbaitapbuoi4.DAO.repositories.RecordRepository;
import com.example.vietisbaitapbuoi4.services.browser_utils.GoogleUtil;
import com.example.vietisbaitapbuoi4.services.browser_utils.YahooUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SearchService {

    @Autowired
    GoogleUtil googleUtil;
    @Autowired
    YahooUtil yahooUtil;

    public void performDailySearch() {
        // Use ExecutorService with 2 threads


        googleUtil.performDailySearch();
        yahooUtil.performDailySearch();

//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//
//        // Run mutiply Google and Yahoo
//        executorService.submit(googleUtil::performDailySearch);
//        executorService.submit(yahooUtil::performDailySearch);
//
//        // Close ExecutorService
//        executorService.shutdown();

    }

}


