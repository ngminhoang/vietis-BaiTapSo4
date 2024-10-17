package com.example.vietisbaitapbuoi4.DAO.repositories;

import com.example.vietisbaitapbuoi4.DAO.entities.SearchRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<SearchRecord, Long> {
}
