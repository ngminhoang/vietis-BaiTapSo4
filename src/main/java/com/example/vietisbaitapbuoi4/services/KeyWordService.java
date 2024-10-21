package com.example.vietisbaitapbuoi4.services;

import com.example.vietisbaitapbuoi4.DAO.entities.DTOs.KeyWordRequestDTO;
import com.example.vietisbaitapbuoi4.DAO.entities.DTOs.KeyWordResponseDTO;
import com.example.vietisbaitapbuoi4.DAO.entities.KeyWord;
import com.example.vietisbaitapbuoi4.DAO.repositories.KeyWordRepository;
import org.checkerframework.checker.index.qual.SearchIndexBottom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class KeyWordService {
    @Autowired
    KeyWordRepository keyWordRepository;
    public ResponseEntity<KeyWordResponseDTO> create(KeyWordRequestDTO keyWordRequestDTO) {
        KeyWord keyWord = new KeyWord(keyWordRequestDTO);
        return ResponseEntity.ok(new KeyWordResponseDTO(keyWordRepository.save(keyWord)));
    }

}
