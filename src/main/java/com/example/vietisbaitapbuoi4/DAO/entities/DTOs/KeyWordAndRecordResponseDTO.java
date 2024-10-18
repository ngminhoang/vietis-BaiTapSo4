package com.example.vietisbaitapbuoi4.DAO.entities.DTOs;

import com.example.vietisbaitapbuoi4.DAO.entities.SearchRecord;
import com.example.vietisbaitapbuoi4.DAO.entities.enums.FOUDATION;

import java.util.List;

public class KeyWordAndRecordResponseDTO {
    public String keyWord;
    public List<String> displayKeyWords;
    public FOUDATION foudation;
    public List<SearchRecord> records;

    public KeyWordAndRecordResponseDTO() {}

    public KeyWordAndRecordResponseDTO(String keyWord, List<String> displayKeyWords, FOUDATION foudation, List<SearchRecord> records) {
        this.keyWord = keyWord;
        this.foudation = foudation;
        this.records = records;
        this.displayKeyWords = displayKeyWords;
    }
}
