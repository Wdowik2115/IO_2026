package vod.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MovieDTO {

    @NotBlank
    private String title;

    private String poster;

    private float rating;

    @NotNull
    private Integer directorId;

    public String getTitle() { return title; }
    public String getPoster() { return poster; }
    public float getRating() { return rating; }
    public Integer getDirectorId() { return directorId; }
}

