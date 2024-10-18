package com.example.vietisbaitapbuoi4.controllers;

import com.example.vietisbaitapbuoi4.DAO.entities.DTOs.KeyWordAndRecordResponseDTO;
import com.example.vietisbaitapbuoi4.DAO.entities.KeyWord;
import com.example.vietisbaitapbuoi4.DAO.entities.SearchRecord;
import com.example.vietisbaitapbuoi4.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class APIController {
    @Autowired
    RecordService recordService;

    @GetMapping("/get_crawled_data")
    public ResponseEntity<List<KeyWordAndRecordResponseDTO>> getCrawledData(@RequestParam int month, @RequestParam int year) {
        return recordService.getCrawledData(month,year);
    }


}
