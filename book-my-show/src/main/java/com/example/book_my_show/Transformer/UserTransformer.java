package com.example.book_my_show.Transformer;

import com.example.book_my_show.Dtos.RequestDtos.AddUserDto;
import com.example.book_my_show.Dtos.ResponseDtos.UserResponseDto;
import com.example.book_my_show.Models.User;

//@NoArgsConstructor and @AllArgsConstructor are required to use in entitty class
// when we use builder for it

public class UserTransformer
{
    public static User convertDtoToEntity(AddUserDto userdto)
    {
        return User.builder()
                .name(userdto.getName())
                .age(userdto.getAge())
                .email(userdto.getEmail())
                .mobNo(userdto.getMobNo())
                .build();

    }
    public static UserResponseDto convertEntityToDto(User user)
    {
       return UserResponseDto.builder()
               .name(user.getName())
               .age(user.getAge())
               .mobNo(user.getMobNo())
               .build();
    }
}
