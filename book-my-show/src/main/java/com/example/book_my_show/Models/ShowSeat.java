package com.example.book_my_show.Models;

import com.example.book_my_show.Enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;

// virtual representation of theatre seat...
@Entity
@Table(name = "show_seats")
@Data
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private int price;  // price stored for each seat..

    private boolean isAvailable;

    private boolean isFoodAttached;

    // Link to the Show entity
    @ManyToOne
    @JoinColumn
    private Show show;

    // Link to the Theatre entity
    @ManyToOne
    @JoinColumn(name = "theatre_id")  // optional: specify the column name for clarity
    private Theatre theatre;  // Reference to the theatre where the seat is located
}
