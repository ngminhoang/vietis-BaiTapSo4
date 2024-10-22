package com.example.vietisbaitapbuoi4.DAO.entities;

import com.example.vietisbaitapbuoi4.DAO.entities.DTOs.KeyWordRequestDTO;
import com.example.vietisbaitapbuoi4.DAO.entities.enums.ACCURATE_TYPE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Thực thể KeyWord lưu trữ từ khóa và các bản ghi tìm kiếm liên quan.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "key_word") // Tên bảng
public class KeyWord {

    /**
     * Khóa chính, tự động tạo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Loại độ chính xác của từ khóa, mặc định là HALF.
     */
    private ACCURATE_TYPE accurate_type = ACCURATE_TYPE.PARTIAL;

    /**
     * Tên từ khóa.
     */
    private String name;

    /**
     * Danh sách từ khóa hiển thị.
     */
    @ElementCollection
    private List<String> displayKeyWords;

    /**
     * Mối quan hệ với SearchRecord, sử dụng lazy load, cascade ALL, và bỏ qua khi chuyển JSON.
     */
    @OneToMany(mappedBy = "keyWord", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SearchRecord> records;

    /**
     * Tạo thực thể KeyWord từ KeyWordRequestDTO.
     */
    public KeyWord(KeyWordRequestDTO keyWordRequestDTO) {
        this.name = keyWordRequestDTO.getName();
        this.displayKeyWords = keyWordRequestDTO.getDisplayKeyWords();
        this.accurate_type = keyWordRequestDTO.getAccurate_type();
    }
}
