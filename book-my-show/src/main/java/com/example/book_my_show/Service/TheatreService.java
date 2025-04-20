package com.example.book_my_show.Service;

import com.example.book_my_show.Dtos.RequestDtos.TheatreEntryDto;
import com.example.book_my_show.Dtos.RequestDtos.TheatreSeatEntryDto;
import com.example.book_my_show.Enums.SeatType;
import com.example.book_my_show.Models.Theatre;
import com.example.book_my_show.Models.TheatreSeat;
import com.example.book_my_show.Repository.TheatreRepository;
import com.example.book_my_show.Transformer.TheatreTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreService
{
    @Autowired
    private TheatreRepository theatreRepository;
   public String addTheatre(TheatreEntryDto theatreEntryDto)
   {
       //entity that save in db
       //convert the entryDto to entitty and then save it
       Theatre theatre = TheatreTransformer.convertDtoToEntitty(theatreEntryDto);

       theatreRepository.save(theatre);

       return "Theatre added successfully";

   }
   public String addTheatreSeats(TheatreSeatEntryDto theatreSeatEntryDto)
   {
       // we need to save theatreSeat entity
       int column = theatreSeatEntryDto.getNoOfSeatIn1Row();
       int noOfClassicSeats = theatreSeatEntryDto.getNoOfClassicSeats();
       int noOfPremiumSeats = theatreSeatEntryDto.getNoOfPremiumSeats();

       String location = theatreSeatEntryDto.getLocation();

       //finding theatre with that location in theatreRepo

       Theatre theatre = theatreRepository.findByLocation(location);
       if (theatre == null) {
           throw new RuntimeException("Theatre not found at location: " + location);
       }

       List<TheatreSeat> theatreSeatList = theatre.getTheatreSeatList();

       int counter =1;
       char ch ='A';
       // for classic seats
       for(int count =1; count<noOfClassicSeats;count++)
       {
           String seatNo= counter+"";
           seatNo = seatNo+ch;

           ch++;
           if((ch-'A'+1)==column)
           {
               ch='A';
               counter++;
           }


           TheatreSeat theatreSeat = new TheatreSeat();
           theatreSeat.setSeatNo(seatNo);
           theatreSeat.setTheatre(theatre);// storing parent info in child
           theatreSeat.setSeatType(SeatType.CLASSIC);
           //this is bidirectional mapping...
           // storing child entity in parent entity attribute
           theatreSeatList.add(theatreSeat);


       }
       // for premium seats
       for(int count =1; count<noOfPremiumSeats;count++)
       {
           String seatNo= counter+"";
           seatNo = seatNo+ch;
           ch++;

           if((ch-'A'+1)==column)
           {
               ch='A';
               counter++;
           }
           TheatreSeat theatreSeat = new TheatreSeat();
           theatreSeat.setSeatNo(seatNo);
           theatreSeat.setTheatre(theatre); // storing parent info in child
           theatreSeat.setSeatType(SeatType.PREMIUM);
           //this is bidirectional mapping...
           // storing child entity in parent entity attribute
           theatreSeatList.add(theatreSeat);
       }
       // Now we just need to save the parent : Theatre entity
       //child will automatically get saved bcz of biDirectn mapping
       theatreRepository.save(theatre);

       return "Theatre seat added succesfully";



   }
}
