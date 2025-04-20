package com.example.book_my_show.Dtos.RequestDtos;

import lombok.Data;

@Data
public class TheatreSeatEntryDto
{
    private String location;

    private int noOfSeatIn1Row;

    private int noOfClassicSeats;

    private int noOfPremiumSeats;

}
