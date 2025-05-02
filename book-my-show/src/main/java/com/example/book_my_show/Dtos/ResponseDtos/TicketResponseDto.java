package com.example.book_my_show.Dtos.ResponseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDto
{
    private LocalTime showTime;
    private LocalDate showDate;
    private String movieName;
    private String theatreName;
    private String bookedSeats;
    private String location;
}
