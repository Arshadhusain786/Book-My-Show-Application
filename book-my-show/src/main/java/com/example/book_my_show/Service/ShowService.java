package com.example.book_my_show.Service;

import com.example.book_my_show.Dtos.RequestDtos.AddShowDto;
import com.example.book_my_show.Dtos.RequestDtos.ShowSeatsDto;
import com.example.book_my_show.Enums.SeatType;
import com.example.book_my_show.Exception.MoviesNotFoundException;
import com.example.book_my_show.Exception.ShowNotFoundException;
import com.example.book_my_show.Exception.TheatreNotFoundException;
import com.example.book_my_show.Models.*;
import com.example.book_my_show.Repository.MovieRepository;
import com.example.book_my_show.Repository.ShowRepository;
import com.example.book_my_show.Repository.TheatreRepository;
import com.example.book_my_show.Transformer.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private ShowRepository showRepository;

    // Method to add a new show
    public String addShow(AddShowDto showDto) throws TheatreNotFoundException, MoviesNotFoundException {
        // Convert show DTO to entity
        Show show = ShowTransformer.convertDtoToEntity(showDto);

        // Set the movie entity
        Optional<Movie> movieOptional = movieRepository.findById(showDto.getMovieId());
        if (!movieOptional.isPresent()) {
            throw new MoviesNotFoundException("Movie not found");
        }
        Movie movie = movieOptional.get();

        // Set the theatre entity
        Optional<Theatre> theatreOptional = theatreRepository.findById(showDto.getTheatreId());
        if (!theatreOptional.isPresent()) {
            throw new TheatreNotFoundException("Theatre not found");
        }
        Theatre theatre = theatreOptional.get();

        // Set movie and theatre in the show entity
        show.setMovie(movie);
        show.setTheatre(theatre);

        // Save the show and get the show ID
        show = showRepository.save(show);

        // Update bidirectional mappings
        movie.getShowList().add(show);
        movieRepository.save(movie);

        theatre.getShowList().add(show);
        theatreRepository.save(theatre);

        return "Show has been added. Show ID: " + show.getId();
    }

    // Method to associate seats with the show (fix for seat duplication issue)
    public String associateShowSeats(ShowSeatsDto showSeatsDto) throws ShowNotFoundException {
        Optional<Show> optionalShow = showRepository.findById(showSeatsDto.getShowId());

        // Check if show exists
        if (!optionalShow.isPresent()) {
            throw new ShowNotFoundException("Invalid show ID");
        }

        Show show = optionalShow.get();
        Theatre theatre = show.getTheatre();

        // Validate if theatre seats exist
        List<TheatreSeat> theatreSeatList = theatre.getTheatreSeatList();
        if (theatreSeatList == null || theatreSeatList.isEmpty()) {
            return "No seats available in the theatre.";
        }

        // List to hold the new ShowSeats
        List<ShowSeat> showSeatList = show.getShowSeatList();

        // Prevent reusing the same seat in the same show
        for (TheatreSeat theatreSeat : theatreSeatList) {
            boolean seatAlreadyAssigned = false;

            // Check if this seat has already been associated with the show
            for (ShowSeat showSeat : showSeatList) {
                if (showSeat.getSeatNo().equals(theatreSeat.getSeatNo())) {
                    seatAlreadyAssigned = true;
                    break; // Exit the loop early if seat is found
                }
            }

            // If the seat is already assigned, skip it
            if (seatAlreadyAssigned) {
                continue; // Skip adding this seat again
            }

            // Create a new ShowSeat if it doesn't already exist for the show
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNo(theatreSeat.getSeatNo());
            showSeat.setSeatType(theatreSeat.getSeatType());

            // Set price based on seat type
            showSeat.setPrice(
                    showSeat.getSeatType().equals(SeatType.CLASSIC) ? showSeatsDto.getPriceForClassicSeats()
                            : showSeatsDto.getPriceForPremiumSeats()
            );

            // Set other properties
            showSeat.setShow(show);
            showSeat.setAvailable(true); // Assume all seats are available initially
            showSeat.setFoodAttached(false); // Assume no food is attached initially

            // Add the ShowSeat to the show
            showSeatList.add(showSeat);
        }

        // Save the show with associated ShowSeats
        showRepository.save(show);

        return "Show seats have been successfully added.";
    }

    // Method to delete a show by its ID
    public String deleteShowById(int id) {
        if (showRepository.existsById(id)) {
            showRepository.deleteById(id);
            return "Show with ID " + id + " has been deleted successfully.";
        } else {
            return "Show with ID " + id + " does not exist.";
        }
    }
}
