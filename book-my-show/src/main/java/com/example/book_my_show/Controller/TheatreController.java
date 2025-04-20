package com.example.book_my_show.Controller;

import com.example.book_my_show.Dtos.RequestDtos.TheatreEntryDto;
import com.example.book_my_show.Dtos.RequestDtos.TheatreSeatEntryDto;
import com.example.book_my_show.Models.Theatre;
import com.example.book_my_show.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theatre")
public class TheatreController
{
    @Autowired
    private TheatreService theatreService;

    @PostMapping("/add")
    public String addTheatre(@RequestBody  TheatreEntryDto theatreEntryDto)
    {
      return theatreService.addTheatre(theatreEntryDto);
    }

    @PostMapping("/addTheatreSeats")
    public String addTheatreSeats(@RequestBody TheatreSeatEntryDto theatreSeatEntryDto)
    {
        return theatreService.addTheatreSeats(theatreSeatEntryDto);
    }

}
