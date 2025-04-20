package com.example.book_my_show.Models;


import com.example.book_my_show.Enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="theatre_seat")
public class TheatreSeat
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn
    private Theatre theatre;


}