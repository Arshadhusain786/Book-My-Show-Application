package com.example.book_my_show.Dtos.RequestDtos;

import lombok.Data;
import java.util.List;

@Data
public class TicketRequestDto
{
    private int  showId;

    private int userId;

    private List<String> requestedSeats;
}
