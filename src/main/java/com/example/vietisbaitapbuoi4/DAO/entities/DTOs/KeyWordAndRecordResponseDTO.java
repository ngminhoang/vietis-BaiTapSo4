package com.example.vietisbaitapbuoi4.DAO.entities.DTOs;

import com.example.vietisbaitapbuoi4.DAO.entities.SearchRecord;
import com.example.vietisbaitapbuoi4.DAO.entities.enums.ACCURATE_TYPE;
import com.example.vietisbaitapbuoi4.DAO.entities.enums.FOUDATION;

import java.util.List;

public class KeyWordAndRecordResponseDTO {
    public String keyWord;
    public ACCURATE_TYPE accurateType;
    public List<String> displayKeyWords;
    public FOUDATION foudation;
    public List<SearchRecord> records;

    public KeyWordAndRecordResponseDTO() {}

    public KeyWordAndRecordResponseDTO(String keyWord, List<String> displayKeyWords, ACCURATE_TYPE accurateType, FOUDATION foudation, List<SearchRecord> records) {
        this.keyWord = keyWord;
        this.foudation = foudation;
        this.records = records;
        this.accurateType = accurateType;
        this.displayKeyWords = displayKeyWords;
    }
}
