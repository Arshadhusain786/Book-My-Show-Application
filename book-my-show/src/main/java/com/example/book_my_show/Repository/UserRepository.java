package com.example.book_my_show.Repository;

import com.example.book_my_show.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>
{
    // This is custom function you have defined
    @Query(value = "SELECT * FROM users WHERE age > :value ;",nativeQuery = true)
    List<User> findUserWithAgeGreater(Integer value);

    //?1 means first paramter we haved passed in custom query method i.e. age

    // You need to write a query on top of this

}
