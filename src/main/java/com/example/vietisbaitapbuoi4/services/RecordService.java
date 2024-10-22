package com.example.vietisbaitapbuoi4.services;

import com.example.vietisbaitapbuoi4.DAO.entities.DTOs.KeyWordAndRecordResponseDTO;
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

    /**
     * Lấy dữ liệu đã crawl theo tháng và năm.
     */
    public ResponseEntity<List<KeyWordAndRecordResponseDTO>> getCrawledData(int month, int year) {
        // Lấy tất cả các bản ghi theo tháng và năm
        List<SearchRecord> records = recordRepository.findRecordsByMonthAndYear(month, year);

        // Nhóm bản ghi theo từ khóa và nền tảng
        Map<String, Map<FOUDATION, List<SearchRecord>>> groupedRecords = groupRecords(records);

        // Tạo danh sách response DTOs
        List<KeyWordAndRecordResponseDTO> response = createResponseDTOs(groupedRecords);

        return ResponseEntity.ok(response);
    }

    /**
     * Nhóm các bản ghi theo từ khóa và nền tảng.
     */
    private Map<String, Map<FOUDATION, List<SearchRecord>>> groupRecords(List<SearchRecord> records) {
        return records.stream()
                .collect(Collectors.groupingBy(
                        record -> record.getKeyWord().getName(), // Nhóm theo tên từ khóa
                        Collectors.groupingBy(SearchRecord::getFoudation, // Nhóm theo nền tảng
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        this::removeDuplicatesByDate // Xóa các record trùng searchDate
                                )
                        )
                ));
    }

    private List<SearchRecord> removeDuplicatesByDate(List<SearchRecord> list) {
        // Sử dụng TreeMap để giữ lại record cuối cùng với cùng searchDate
        return new ArrayList<>(list.stream()
                .collect(Collectors.toMap(
                        SearchRecord::getSearchDate, // Key là searchDate
                        record -> record, // Value là record
                        (existing, replacement) -> replacement, // Nếu trùng searchDate, giữ lại record cuối cùng
                        TreeMap::new // Sử dụng TreeMap để đảm bảo thứ tự
                ))
                .values()); // Lấy danh sách các giá trị cuối cùng
    }

    /**
     * Tạo danh sách các DTO từ các bản ghi đã nhóm.
     */
    private List<KeyWordAndRecordResponseDTO> createResponseDTOs(Map<String, Map<FOUDATION, List<SearchRecord>>> groupedRecords) {
        List<KeyWordAndRecordResponseDTO> response = new ArrayList<>();

        for (Map.Entry<String, Map<FOUDATION, List<SearchRecord>>> keywordEntry : groupedRecords.entrySet()) {
            String keyWordName = keywordEntry.getKey(); // Lấy tên từ khóa

            for (Map.Entry<FOUDATION, List<SearchRecord>> foundationEntry : keywordEntry.getValue().entrySet()) {
                FOUDATION foundation = foundationEntry.getKey(); // Lấy loại nền tảng

                // Sắp xếp các bản ghi theo ngày tìm kiếm
                List<SearchRecord> recordsForFoundation = sortRecordsByDate(foundationEntry.getValue());

                // Lấy từ khóa hiển thị và kiểu chính xác từ bản ghi đầu tiên
                List<String> displayKeyWords = recordsForFoundation.get(0).getKeyWord().getDisplayKeyWords();
                ACCURATE_TYPE accurateType = recordsForFoundation.get(0).getKeyWord().getAccurate_type();

                // Xử lý các gợi ý và phát hiện từ khóa phù hợp
                detectKeyWords(recordsForFoundation, keyWordName, displayKeyWords, accurateType);

                // Tạo DTO và thêm vào danh sách response
                KeyWordAndRecordResponseDTO dto = new KeyWordAndRecordResponseDTO(keyWordName, displayKeyWords, accurateType, foundation, recordsForFoundation);
                response.add(dto);
            }
        }
        return response;
    }

    /**
     * Sắp xếp các bản ghi theo ngày tìm kiếm từ quá khứ đến hiện tại.
     */
    private List<SearchRecord> sortRecordsByDate(List<SearchRecord> records) {
        return records.stream()
                .sorted(Comparator.comparing(SearchRecord::getSearchDate)) // Sắp xếp theo searchDate
                .collect(Collectors.toList());
    }

    /**
     * Xử lý các gợi ý và phát hiện từ khóa phù hợp.
     */
    private void detectKeyWords(List<SearchRecord> records, String keyWordName, List<String> displayKeyWords, ACCURATE_TYPE accurateType) {
        for (SearchRecord record : records) {
            for (String displayKeyWord : displayKeyWords) {
                String checkKeyWord = keyWordName + " " + displayKeyWord;
                List<String> suggestions = record.getSuggestions();

                // Kiểm tra từ khóa và cập nhật danh sách gợi ý
                for (int i = 0; i < suggestions.size(); i++) {
                    String checkSuggestion = suggestions.get(i);
                    if (accurateType.equals(ACCURATE_TYPE.UNANIMOUS) && checkSuggestion.equals(checkKeyWord)) {
                        suggestions.set(i, "detect_" + checkSuggestion);
                        break;
                    } else if (accurateType.equals(ACCURATE_TYPE.PARTIAL) && checkSuggestion.contains(displayKeyWord)) {
                        suggestions.set(i, "detect_" + checkSuggestion);
                        break;
                    }
                }
            }
        }
    }
}
