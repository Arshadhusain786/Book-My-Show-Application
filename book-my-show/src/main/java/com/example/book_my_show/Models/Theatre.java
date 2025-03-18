package com.example.book_my_show.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="theatre")
public class Theatre
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String location;

    @OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL)
    @JoinColumn
    private List<TheatreSeat> theatreSeatList= new ArrayList<>();

}
