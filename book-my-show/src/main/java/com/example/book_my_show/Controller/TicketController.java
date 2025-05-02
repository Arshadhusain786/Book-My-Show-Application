package com.example.book_my_show.Controller;

import com.example.book_my_show.Dtos.RequestDtos.TicketRequestDto;
import com.example.book_my_show.Dtos.ResponseDtos.TicketResponseDto;
import com.example.book_my_show.Models.Ticket;
import com.example.book_my_show.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ticket")
public class TicketController
{
    @Autowired
    private TicketService ticketService;

    @PostMapping("/book-ticket")
    public TicketResponseDto bookTicket(@RequestBody TicketRequestDto ticketRequestDto)
    {
        try{
           return ticketService.bookTicket(ticketRequestDto);
        }
        catch(Exception e)
        {
           return new TicketResponseDto();
        }

    }


}
