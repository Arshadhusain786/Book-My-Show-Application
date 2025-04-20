package com.example.book_my_show.Dtos.ResponseDtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserResponseDto
{

    private String name;
    private int age;
    private String mobNo;

    private String statusCode;
    private String statusMessage;


}
