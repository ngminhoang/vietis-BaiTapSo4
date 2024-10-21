package com.example.vietisbaitapbuoi4.DAO.entities.DTOs;

import com.example.vietisbaitapbuoi4.DAO.entities.enums.ACCURATE_TYPE;
import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class KeyWordRequestDTO {

    private ACCURATE_TYPE accurate_type;

    private String name;

    private List<String> displayKeyWords;
}

