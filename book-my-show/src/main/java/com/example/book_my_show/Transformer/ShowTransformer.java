package com.example.book_my_show.Transformer;

import com.example.book_my_show.Dtos.RequestDtos.AddShowDto;
import com.example.book_my_show.Models.Show;


public class ShowTransformer
{
    public static Show convertDtoToEntity(AddShowDto addShowDto)
    {
        return Show.builder()
                .time(addShowDto.getShowStartTime())
                .date(addShowDto.getShowDate())
                .build();
    }
}
