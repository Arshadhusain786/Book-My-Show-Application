package com.example.book_my_show.Models;


import com.example.book_my_show.Enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="theatre_seat")
public class TheatreSeat
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String seatNo;

    private SeatType seatType;

    @ManyToOne
    @JoinColumn
    private Theatre theatre;

}
