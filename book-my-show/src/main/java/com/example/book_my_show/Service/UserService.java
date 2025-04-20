package com.example.book_my_show.Service;

import com.example.book_my_show.Dtos.RequestDtos.AddUserDto;
import com.example.book_my_show.Dtos.ResponseDtos.UserResponseDto;
import com.example.book_my_show.Exception.NoUserFoundException;
import com.example.book_my_show.Models.User;
import com.example.book_my_show.Repository.UserRepository;
import com.example.book_my_show.Transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;

    public String  addUser(AddUserDto userdto)
    {
        // conversion of userDto to user
//       User user = new User();
//       user.setName(userdto.getName());
//       user.setAge(userdto.getAge());
//       user.setEmail(userdto.getEmail());
//       user.setMobNo(userdto.getMobNo());

       // we can avoid these above step using transformer
        User user = UserTransformer.convertDtoToEntity(userdto);
        userRepository.save(user);

        return "User has been added succesfully";

    }
    public UserResponseDto getOldestUser() throws NoUserFoundException
    {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            return null; // Handle the case when no users exist
        }

        int maxAge = 0;
        User oldestUser= null;

        for (User user: users) {
            if (user.getAge() > maxAge) {
                maxAge = user.getAge();
                oldestUser = user;
            }
        }
       if(oldestUser==null) {
           throw new NoUserFoundException("No such user found");
       }

        // we need to transform user entity to userResponseDto
        UserResponseDto userResponseDto = UserTransformer.convertEntityToDto(oldestUser);

        return userResponseDto;
    }
    public List<User> getAllUsersGreaterThan(Integer age)
    {
       List<User> users = userRepository.findUserWithAgeGreater(age);
       return  users;
    }

}
