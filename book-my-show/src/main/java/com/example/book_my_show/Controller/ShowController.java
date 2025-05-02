package com.example.book_my_show.Controller;

import com.example.book_my_show.Dtos.RequestDtos.AddShowDto;
import com.example.book_my_show.Dtos.RequestDtos.ShowSeatsDto;
import com.example.book_my_show.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController
{
    @Autowired
    private ShowService showService;

    @PostMapping("/add")
    public String addShow(@RequestBody AddShowDto addShowDto)
    {
        try{
            return showService.addShow(addShowDto);
        }
        catch(Exception e)
        {
            return e.getMessage();
        }

    }
    @PostMapping("/associate-seats")
    public String associateSeats(@RequestBody ShowSeatsDto showSeatsDto)
    {
        try{
            return showService.associateShowSeats(showSeatsDto);
        }
        catch(Exception e)
        {
            return e.getMessage();
        }

    }
    @DeleteMapping("/delete/{id}")
    public String deleteShowById(@PathVariable int id)
    {
        try {
            return showService.deleteShowById(id);
        }
        catch (Exception e)
        {
            return "Error deleting show: " + e.getMessage();
        }
    }

}
