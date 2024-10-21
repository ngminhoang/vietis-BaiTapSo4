package com.example.vietisbaitapbuoi4.DAO.entities;

import com.example.vietisbaitapbuoi4.DAO.entities.DTOs.KeyWordRequestDTO;
import com.example.vietisbaitapbuoi4.DAO.entities.enums.ACCURATE_TYPE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "key_word")
public class KeyWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ACCURATE_TYPE accurate_type = ACCURATE_TYPE.HALF;

    private String name;

    @ElementCollection
    private List<String> displayKeyWords;

    @OneToMany(mappedBy = "keyWord", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SearchRecord> records;

    public KeyWord(KeyWordRequestDTO keyWordRequestDTO) {
        this.name = keyWordRequestDTO.getName();
        this.displayKeyWords = keyWordRequestDTO.getDisplayKeyWords();
        this.accurate_type = keyWordRequestDTO.getAccurate_type();
    }
}
