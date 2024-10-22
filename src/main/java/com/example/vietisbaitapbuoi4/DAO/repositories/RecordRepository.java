package com.example.vietisbaitapbuoi4.DAO.repositories;

import com.example.vietisbaitapbuoi4.DAO.entities.SearchRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RecordRepository extends JpaRepository<SearchRecord, Long> {

    /**
     * Truy vấn tìm kiếm các bản ghi theo tháng và năm.
     *
     * @param month Tháng của bản ghi tìm kiếm.
     * @param year Năm của bản ghi tìm kiếm.
     * @return Danh sách các SearchRecord tìm thấy.
     */
    @Query("SELECT sr FROM SearchRecord sr WHERE MONTH(sr.searchDate) = :month AND YEAR(sr.searchDate) = :year")
    List<SearchRecord> findRecordsByMonthAndYear(@Param("month") int month, @Param("year") int year);
}
