package com.example.book_my_show.Controller;

import com.example.book_my_show.Dtos.RequestDtos.AddUserDto;

import com.example.book_my_show.Dtos.ResponseDtos.UserResponseDto;
import com.example.book_my_show.Exception.NoUserFoundException;
import com.example.book_my_show.Models.User;
import com.example.book_my_show.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public String addUser(@RequestBody AddUserDto userDto) {
        try {
            String result =  userService.addUser(userDto);
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/get-oldest")
    public UserResponseDto getOldestUser() throws NoUserFoundException
    {
        try{
            UserResponseDto userResponseDto = userService.getOldestUser();
            userResponseDto.setStatusCode("200");
            userResponseDto.setStatusMessage("SUCCESS");
            return userResponseDto;
        }
        catch(Exception e)
        {
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setStatusCode("500");
            userResponseDto.setStatusMessage("FAILURE");
            return userResponseDto;

        }

    }
    @GetMapping("/findUsersGreaterThanAge")
    public List<User> getAllUsers(@RequestParam("age")Integer age)
    {
       return userService.getAllUsersGreaterThan(age);
    }
}
