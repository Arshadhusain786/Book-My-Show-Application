
package com.example.book_my_show.Dtos.RequestDtos;
import com.example.book_my_show.Enums.Genre;
import com.example.book_my_show.Enums.Language;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class MovieEntryDto {

    @NotBlank(message = "Movie name cannot be blank")
    private String name;

    @Positive(message = "Duration must be positive")
    private double duration;

    @Positive(message = "Rating must be positive")
    private double rating;

    @PastOrPresent(message = "Release date cannot be in the future")
    private Date releasedDate;

    @NotNull(message = "Language must be provided")
    private Language language;

    @NotNull(message = "Genre must be provided")
    private Genre genre;
}
