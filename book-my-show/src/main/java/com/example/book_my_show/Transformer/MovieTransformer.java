package com.example.book_my_show.Transformer;

import com.example.book_my_show.Dtos.RequestDtos.MovieEntryDto;
import com.example.book_my_show.Models.Movie;

public class MovieTransformer
{
    public static Movie convertDtoToEntity(MovieEntryDto movieEntryDto)
    {
        Movie movie = Movie.builder().name(movieEntryDto.getName())
                .duration(movieEntryDto.getDuration())
                .genre(movieEntryDto.getGenre())
                .language(movieEntryDto.getLanguage())
                .rating(movieEntryDto.getRating())
                .releasedDate(movieEntryDto.getReleasedDate())
                .build();
        return movie;
    }
}