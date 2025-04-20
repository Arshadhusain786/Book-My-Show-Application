package com.example.book_my_show.Repository;

import com.example.book_my_show.Models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre,Integer>
{

    Theatre findByLocation(String location);
}
