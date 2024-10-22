package com.example.vietisbaitapbuoi4.controllers;

import com.example.vietisbaitapbuoi4.DAO.entities.DTOs.KeyWordAndRecordResponseDTO;
import com.example.vietisbaitapbuoi4.services.RecordService;
import com.example.vietisbaitapbuoi4.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("isSecondVisible")
public class ViewController {

    @Autowired
    RecordService recordService;
    @Autowired
    SearchService searchService;

    @GetMapping("/dashboard-display-1")
    public String showDashboardDisplay1(
            @RequestParam(value = "month", defaultValue = "#{T(java.time.LocalDate).now().getMonthValue()}") int month,
            @RequestParam(value = "year", defaultValue = "#{T(java.time.LocalDate).now().getYear()}") int year,
            Model model) {
        List<KeyWordAndRecordResponseDTO> data = recordService.getCrawledData(month, year).getBody(); // Lấy dữ liệu từ service
        System.out.println(data);
        model.addAttribute("data", data); // Thêm dữ liệu vào model
        return "dashboard-display-1"; // Tên template Thymeleaf
    }

    @GetMapping("/dashboard-display-2")
    public String showDashboardDisplay2(
            @RequestParam(value = "month", defaultValue = "#{T(java.time.LocalDate).now().getMonthValue()}") int month,
            @RequestParam(value = "year", defaultValue = "#{T(java.time.LocalDate).now().getYear()}") int year,
            Model model) {
        List<KeyWordAndRecordResponseDTO> data = recordService.getCrawledData(month, year).getBody(); // Lấy dữ liệu từ service
        System.out.println(data);
        model.addAttribute("searchService", searchService);
        model.addAttribute("data", data); // Thêm dữ liệu vào model
        return "dashboard-display-2"; // Tên template Thymeleaf
    }

}
