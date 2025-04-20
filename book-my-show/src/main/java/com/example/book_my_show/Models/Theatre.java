package com.example.book_my_show.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="theatre")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Theatre
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String location;

    @OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL)
    // cascade means top to down flow..
    // hence if we save or delete parent it will auto save or delete child
    private List<TheatreSeat> theatreSeatList= new ArrayList<>();


    @OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL)
    // cascade means top to down flow..
    // hence if we save or delete parent it will auto save or delete child
    private List<Show> showList= new ArrayList<>();


}


