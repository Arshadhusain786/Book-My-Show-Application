package com.example.book_my_show.Transformer;

import com.example.book_my_show.Dtos.RequestDtos.TheatreEntryDto;
import com.example.book_my_show.Models.Theatre;

public class TheatreTransformer
{
    public static Theatre convertDtoToEntitty(TheatreEntryDto theatreEntryDto)
    {
        Theatre theatre = Theatre.builder().
                location(theatreEntryDto.getLocation())
                .name(theatreEntryDto.getName())
                .build();

        return theatre;
    }
}
