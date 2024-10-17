package com.example.vietisbaitapbuoi4.DAO.entities.DTOs;

import java.util.List;

public class KeyWordResponseDTO {

    private Long id;
    private String name;
    private String imgPath;
    private List<RecordResponseDTO> records;

    // Constructors
    public KeyWordResponseDTO() {}

    public KeyWordResponseDTO(Long id, String name, String imgPath, List<RecordResponseDTO> records) {
        this.id = id;
        this.name = name;
        this.imgPath = imgPath;
        this.records = records;
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public List<RecordResponseDTO> getRecords() {
        return records;
    }

    public void setRecords(List<RecordResponseDTO> records) {
        this.records = records;
    }
}
