package com.example.vietisbaitapbuoi4.DAO.repositories;

import com.example.vietisbaitapbuoi4.DAO.entities.KeyWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyWordRepository extends JpaRepository<KeyWord, Long> {
}
