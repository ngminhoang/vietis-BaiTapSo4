package com.example.vietisbaitapbuoi4.controllers;

import com.example.vietisbaitapbuoi4.DAO.entities.DTOs.KeyWordAndRecordResponseDTO;
import com.example.vietisbaitapbuoi4.DAO.entities.DTOs.KeyWordRequestDTO;
import com.example.vietisbaitapbuoi4.DAO.entities.DTOs.KeyWordResponseDTO;
import com.example.vietisbaitapbuoi4.DAO.entities.KeyWord;
import com.example.vietisbaitapbuoi4.DAO.entities.SearchRecord;
import com.example.vietisbaitapbuoi4.services.KeyWordService;
import com.example.vietisbaitapbuoi4.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class APIController {
    @Autowired
    RecordService recordService;
    @Autowired
    KeyWordService keyWordService;

    @GetMapping("/get_crawled_data")
    public ResponseEntity<List<KeyWordAndRecordResponseDTO>> getCrawledData(@RequestParam int month, @RequestParam int year) {
        return recordService.getCrawledData(month,year);
    }

    @PostMapping("/create_key")
    public ResponseEntity<KeyWordResponseDTO> createKey(@RequestBody KeyWordRequestDTO keyWord) {
        return keyWordService.create(keyWord);
    }
}
