package com.example.vietisbaitapbuoi4.services;

import com.example.vietisbaitapbuoi4.DAO.entities.DTOs.KeyWordAndRecordResponseDTO;
import com.example.vietisbaitapbuoi4.DAO.entities.KeyWord;
import com.example.vietisbaitapbuoi4.DAO.entities.SearchRecord;
import com.example.vietisbaitapbuoi4.DAO.entities.enums.ACCURATE_TYPE;
import com.example.vietisbaitapbuoi4.DAO.entities.enums.FOUDATION;
import com.example.vietisbaitapbuoi4.DAO.repositories.KeyWordRepository;
import com.example.vietisbaitapbuoi4.DAO.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecordService {

    @Autowired
    KeyWordRepository keyWordRepository;
    @Autowired
    RecordRepository recordRepository;


    public ResponseEntity<List<KeyWordAndRecordResponseDTO>> getCrawledData(int month, int year) {
        // Fetch all records by month and year
        List<SearchRecord> records = recordRepository.findRecordsByMonthAndYear(month, year);

        // Create a Map to group records first by KeyWord name, then by FOUDATION
        Map<String, Map<FOUDATION, List<SearchRecord>>> groupedRecords = records.stream()
                .collect(Collectors.groupingBy(
                        record -> record.getKeyWord().getName(), // First grouping by KeyWord name
                        Collectors.groupingBy(SearchRecord::getFoudation) // Then grouping by FOUDATION
                ));

        // List to store the response DTOs
        List<KeyWordAndRecordResponseDTO> response = new ArrayList<>();

        // Iterate over the grouped records and create DTOs
        for (Map.Entry<String, Map<FOUDATION, List<SearchRecord>>> keywordEntry : groupedRecords.entrySet()) {
            String keyWordName = keywordEntry.getKey(); // Get the KeyWord name

            for (Map.Entry<FOUDATION, List<SearchRecord>> foundationEntry : keywordEntry.getValue().entrySet()) {
                FOUDATION foundation = foundationEntry.getKey(); // Get the FOUDATION type

                // Sort the records for this FOUDATION by searchDate from past to now
                List<SearchRecord> recordsForFoundation = foundationEntry.getValue().stream()
                        .sorted(Comparator.comparing(SearchRecord::getSearchDate)) // Sort by searchDate
                        .collect(Collectors.toList());
                List<String> displayKeyWords = recordsForFoundation.get(0).getKeyWord().getDisplayKeyWords();
                ACCURATE_TYPE accurateType = recordsForFoundation.get(0).getKeyWord().getAccurate_type();

                //detect the type

                for (SearchRecord record : recordsForFoundation) {
                    for (String displayKeyWord : displayKeyWords) {
                        String checkKeyWord = keyWordName + displayKeyWord;
                        List<String> suggestions = record.getSuggestions();
                        for (int i = 0; i < suggestions.size(); i++) {
                            String checkSuggestion = suggestions.get(i);
                            if (checkSuggestion.equals(checkKeyWord) && accurateType.equals(ACCURATE_TYPE.PERFECT)) {
                                suggestions.set(i, "detect_" + checkSuggestion); // Update the list
                                break;
                            } else if (checkSuggestion.contains(displayKeyWord) && accurateType.equals(ACCURATE_TYPE.HALF)) {
                                suggestions.set(i, "detect_" + checkSuggestion); // Update the list
                                break;
                            }
                        }
                    }
                }





                // Create a new DTO and add it to the response list
                KeyWordAndRecordResponseDTO dto = new KeyWordAndRecordResponseDTO(keyWordName, displayKeyWords, accurateType, foundation, recordsForFoundation);
                response.add(dto);
            }
        }

        return ResponseEntity.ok(response);
    }

}
