package com.example.vietisbaitapbuoi4.DAO.entities.DTOs;

import java.util.List;

public class RecordRequestDTO {

    private List<String> suggestions;

    // Constructors
    public RecordRequestDTO() {}

    public RecordRequestDTO(List<String> suggestions) {
        this.suggestions = suggestions;
    }

    // Getters and Setters
    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }
}
