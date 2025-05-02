package com.example.book_my_show.Service;

import com.example.book_my_show.Dtos.RequestDtos.TicketRequestDto;
import com.example.book_my_show.Dtos.ResponseDtos.TicketResponseDto;
import com.example.book_my_show.Exception.NoUserFoundException;
import com.example.book_my_show.Exception.ShowNotFoundException;
import com.example.book_my_show.Models.Show;
import com.example.book_my_show.Models.ShowSeat;
import com.example.book_my_show.Models.Ticket;
import com.example.book_my_show.Models.User;
import com.example.book_my_show.Repository.ShowRepository;
import com.example.book_my_show.Repository.TicketRepository;
import com.example.book_my_show.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService
{
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;

    public TicketResponseDto bookTicket(TicketRequestDto ticketRequestDto) throws NoUserFoundException, ShowNotFoundException,Exception
    {
        // user validation
        int userId = ticketRequestDto.getUserId();
        Optional<User> userOptional = userRepository.findById(userId);

        if(!userOptional.isPresent())
        {
           throw new NoUserFoundException("user id is incorrect");
        }

        // show validation
        int showId = ticketRequestDto.getShowId();
        Optional<Show> showOptional = showRepository.findById(showId);

        if(!showOptional.isPresent())
        {
            throw new ShowNotFoundException("show not found");
        }
        Show show = showOptional.get();

        // validation for the requested seats are available or not
       boolean isValid = validateRequestedAvailability(show,ticketRequestDto.getRequestedSeats());

       if(isValid==false)
       {
           throw  new Exception("Requested seats enterred are not available");
       }

       Ticket ticket = new Ticket();

       // calculate total price
        int totalPrice = calculateTotalPrice(show,ticketRequestDto.getRequestedSeats());
        ticket.setTotalTicketsPrice(totalPrice);

        // convert the list of booked seats into string from list
        String bookedSeats = convertListToString(ticketRequestDto.getRequestedSeats());
        ticket.setBookedSeats(bookedSeats);

        // do bidirectional mapping
        User user = userOptional.get();

        ticket = ticketRepository.save(ticket);
        user.getTicketList().add(ticket);

        // saving the revelent repositories
        userRepository.save(user);

        show.getTicketList().add(ticket);

        showRepository.save(show);

        return createTicketResponseDto(show,ticket);

    }
    private boolean validateRequestedAvailability(Show show,List<String>requestedSeats)
    {
        List<ShowSeat> showSeatList = show.getShowSeatList();

        for(ShowSeat showSeat : showSeatList)
        {
            String seatNo = showSeat.getSeatNo();

            if(requestedSeats.contains(seatNo) && showSeat.isAvailable()==false)
            {
                return false;
            }
        }
        return  true;

    }
    private int calculateTotalPrice(Show show,List<String>requestedSeats)
    {
        int totalPrice =0;
        List<ShowSeat>showSeatList = show.getShowSeatList();
        for(ShowSeat showSeat: showSeatList)
        {
            if(requestedSeats.contains(showSeat.getSeatNo()))
            {
                totalPrice+= showSeat.getPrice();
                showSeat.setAvailable(false);
            }
        }
        return totalPrice;
    }
    private String convertListToString(List<String> seats)
    {
        String result = "";
        for(String seatNo:seats)
        {
            result=result+seatNo+", ";
        }
        return result;
    }
    private TicketResponseDto createTicketResponseDto(Show show, Ticket ticket)
    {
        TicketResponseDto ticketResponseDto = TicketResponseDto.builder()
                .bookedSeats(ticket.getBookedSeats())
                .location(show.getTheatre().getLocation())
                .theatreName(show.getTheatre().getName())
                .movieName(show.getMovie().getName())
                .showDate(show.getDate())
                .showTime(show.getTime())
                .build();

        return ticketResponseDto;
    }

}
