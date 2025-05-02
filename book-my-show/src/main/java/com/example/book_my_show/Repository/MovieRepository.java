package com.example.book_my_show.Repository;

import com.example.book_my_show.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer>
{
}
