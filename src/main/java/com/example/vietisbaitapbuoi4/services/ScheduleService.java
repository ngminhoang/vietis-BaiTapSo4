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

        // Runs daily at midnight using cron expression
        @Scheduled(cron = "0 0 0 * * ?")
        public void scheduleDailySearch() {
                searchService.performDailySearch();  // Gọi phương thức thực hiện tìm kiếm hàng ngày
        }
}
