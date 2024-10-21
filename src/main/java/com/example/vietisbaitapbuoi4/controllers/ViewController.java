package com.example.vietisbaitapbuoi4.controllers;

import com.example.vietisbaitapbuoi4.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("isSecondVisible")
public class ViewController {

    @Autowired
    RecordService recordService;

    @GetMapping("/dashboard-display-1")
    public String showDashboardDisplay1(Model model) {

        return "dashboard-display-1"; // Thymeleaf template name
    }
    @GetMapping("/dashboard-display-2")
    public String showDashboardDisplay2(Model model) {

        return "dashboard-display-2"; // Thymeleaf template name
    }
//    @GetMapping("/dashboard")
//    public String adminDashboard(@RequestParam(required = false, defaultValue = "1") int month,
//                                 @RequestParam(required = false, defaultValue = "2023") int year,
//                                 Model model) {
//        // Get the crawled data from the service
//        ResponseEntity<List<KeyWordAndRecordResponseDTO>> responseEntity = recordService.getCrawledData(month, year);
//        List<KeyWordAndRecordResponseDTO> data = responseEntity.getBody();
//
//        // Add the data to the model so it can be accessed in the Thymeleaf template
//        model.addAttribute("dashboard", data);
//
//        // Return the Thymeleaf template (data.html)
//        return "dashboard";
//    }

}
