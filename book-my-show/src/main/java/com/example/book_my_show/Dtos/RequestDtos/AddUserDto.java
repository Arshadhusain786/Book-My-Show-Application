package com.example.book_my_show.Dtos.RequestDtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddUserDto
{
    private String name;

    private int age;

    private String mobNo;

    private String email;
}
