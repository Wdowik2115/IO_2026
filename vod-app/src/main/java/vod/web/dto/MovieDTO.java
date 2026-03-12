package vod.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovieDTO {

    @NotBlank
    private String title;

    private String poster;

    private double rating;

    @NotNull
    private Integer directorId;
}
