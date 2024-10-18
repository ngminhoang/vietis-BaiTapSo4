package com.example.vietisbaitapbuoi4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleService {

        @Autowired
        private SearchService searchService;
//        @Scheduled(fixedDelay = 7000L)  // Runs daily at midnight
//        public void scheduleDailySearch() {
//            searchService.performDailySearch();
//        }
}
