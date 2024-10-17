package com.example.vietisbaitapbuoi4.DAO.entities;

import com.example.vietisbaitapbuoi4.DAO.entities.enums.FOUDATION;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "record")
public class SearchRecord {  // Renamed from Record to SearchRecord
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date searchDate;

    @ElementCollection
    private List<String> suggestions;

    private String imgPath;

    private FOUDATION foudation;

    @ManyToOne
    @JoinColumn(name = "keyword_id")
    private KeyWord keyWord;
}
