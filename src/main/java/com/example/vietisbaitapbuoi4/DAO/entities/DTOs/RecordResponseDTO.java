package com.example.vietisbaitapbuoi4.DAO.entities.DTOs;

import com.example.vietisbaitapbuoi4.DAO.entities.SearchRecord;

import java.util.Date;
import java.util.List;

public class RecordResponseDTO {

    private Long id;
    private Date searchDate;
    private List<String> suggestions;

    // Constructors
    public RecordResponseDTO() {}

    public RecordResponseDTO(SearchRecord searchRecord) {
        this.id = searchRecord.getId();
        this.searchDate = searchRecord.getSearchDate();
        this.suggestions = searchRecord.getSuggestions();
    }

    public RecordResponseDTO(Long id, Date searchDate, List<String> suggestions) {
        this.id = id;
        this.searchDate = searchDate;
        this.suggestions = suggestions;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }
}
