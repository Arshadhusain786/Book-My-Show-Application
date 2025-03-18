package com.example.book_my_show.Models;

import com.example.book_my_show.Enums.Genre;
import com.example.book_my_show.Enums.Language;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="movies")
@Data
public class Movie
{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private  int id;

    @Column(nullable = false)  // it means we cant keep the movie name null
    private String name;

    private double duration;

    @Column(scale = 2)
    private double rating;

    private Date releasedDate;

    @Enumerated(EnumType.STRING)
    private Language language;

    @Enumerated(EnumType.STRING)
    private Genre genre;



  // private Show shows;





}
