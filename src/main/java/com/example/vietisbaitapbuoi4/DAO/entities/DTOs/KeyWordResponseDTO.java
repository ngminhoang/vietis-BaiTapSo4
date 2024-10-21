package com.example.vietisbaitapbuoi4.DAO.entities.DTOs;

import com.example.vietisbaitapbuoi4.DAO.entities.KeyWord;
import com.example.vietisbaitapbuoi4.DAO.entities.SearchRecord;

import java.util.List;

public class KeyWordResponseDTO {

    private Long id;
    private String name;
    private List<RecordResponseDTO> records;
    // Constructors
    public KeyWordResponseDTO() {}

    public KeyWordResponseDTO(Long id, String name, List<RecordResponseDTO> records) {
        this.id = id;
        this.name = name;
        this.records = records;
    }



    public KeyWordResponseDTO(KeyWord keyWord) {
        this.id = keyWord.getId();
        this.name = keyWord.getName();
        try{
            this.records = keyWord.getRecords().stream().map(RecordResponseDTO::new).toList();
        }
        catch(Exception e){}
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
