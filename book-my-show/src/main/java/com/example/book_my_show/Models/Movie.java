package com.example.book_my_show.Models;

import com.example.book_my_show.Enums.Genre;
import com.example.book_my_show.Enums.Language;
import com.example.book_my_show.Repository.TheatreRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.TransactionUsageException;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="movies")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Show> showList= new ArrayList<>();





}