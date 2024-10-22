package com.example.vietisbaitapbuoi4.services;

import com.example.vietisbaitapbuoi4.services.browser_utils.GoogleUtil;
import com.example.vietisbaitapbuoi4.services.browser_utils.YahooUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class SearchService {

    @Autowired
    GoogleUtil googleUtil;
    @Autowired
    YahooUtil yahooUtil;

    public boolean anyStartsWithDetect(List<String> suggestions) {
        if (suggestions == null || suggestions.isEmpty()) {
            return false;
        }
        return suggestions.stream().anyMatch(s -> s.startsWith("detect_"));
    }

    public void performDailySearch() {
        // Use ExecutorService with 2 threads
//
//        googleUtil.performDailySearch();
//        yahooUtil.performDailySearch();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Run mutiply Google and Yahoo
        executorService.submit(googleUtil::performDailySearch);
        executorService.submit(yahooUtil::performDailySearch);

        // Close ExecutorService
        executorService.shutdown();

    }

}


