package com.example.vietisbaitapbuoi4.DAO.repositories;

import com.example.vietisbaitapbuoi4.DAO.entities.DTOs.KeyWordAndRecordResponseDTO;
import com.example.vietisbaitapbuoi4.DAO.entities.KeyWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyWordRepository extends JpaRepository<KeyWord, Long> {

    @Query("SELECT kw FROM KeyWord kw INNER JOIN kw.records sr " +
            "WHERE MONTH(sr.searchDate) = :month AND YEAR(sr.searchDate) = :year")
    List<KeyWord> findKeywordsWithRecordsByMonthAndYear(@Param("month") int month, @Param("year") int year);

}
