package com.example.vietisbaitapbuoi4.DAO.entities.DTOs;

import java.util.List;

public class SuggestionResponseDTO {
    public List<String> suggestions;
    public String imgPath;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }

    public SuggestionResponseDTO(List<String> suggestions, String imgPath) {
        this.suggestions = suggestions;
        this.imgPath = imgPath;
    }
}
