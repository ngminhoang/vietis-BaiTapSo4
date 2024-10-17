package com.example.vietisbaitapbuoi4.DAO.entities.DTOs;

import java.util.Date;
import java.util.List;

public class KeyWordRequestDTO {

    private String name;
    private String imgPath;

    // Constructors
    public KeyWordRequestDTO() {}

    public KeyWordRequestDTO(String name, String imgPath) {
        this.name = name;
        this.imgPath = imgPath;
    }

    // Getters and Setters
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
}

