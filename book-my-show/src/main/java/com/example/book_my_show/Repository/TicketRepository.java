package com.example.book_my_show.Repository;

import com.example.book_my_show.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer>
{

}
