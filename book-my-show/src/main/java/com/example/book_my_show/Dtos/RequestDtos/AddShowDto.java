package com.example.book_my_show.Dtos.RequestDtos;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
public class AddShowDto
{
    private LocalTime showStartTime;

    private LocalDate showDate;

    private int theatreId;

    private int movieId;

}
