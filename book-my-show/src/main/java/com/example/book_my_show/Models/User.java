package com.example.book_my_show.Models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name ="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Integer age;
    private String mobNo;
    private String email;

}