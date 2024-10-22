package com.example.vietisbaitapbuoi4.DAO.entities;

import com.example.vietisbaitapbuoi4.DAO.entities.enums.FOUDATION;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Thực thể SearchRecord lưu trữ dữ liệu tìm kiếm.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "record")
public class SearchRecord {  // Đổi tên từ Record thành SearchRecord
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tạo ID
    private Long id;

    @Temporal(TemporalType.DATE) // Định dạng ngày tháng
    private Date searchDate;

    @ElementCollection // Lưu danh sách các gợi ý
    private List<String> suggestions;

    private String imgPath; // Đường dẫn hình ảnh

    private FOUDATION foudation; // Thuộc tính foundation (cơ sở)

    @ManyToOne // Quan hệ nhiều - một với KeyWord
    @JoinColumn(name = "keyword_id") // Khóa ngoại liên kết với KeyWord
    private KeyWord keyWord;
}
