package com.rest.Rest.Service;

import com.rest.Rest.Entity.User;

import java.util.List;

public interface UserService
{
    List<User> findAll();

    User UpdateByUserName(String username,User userRequest);

    User findByUserId(Long id);

    String SaveUser(User user);


    boolean DeleteUserByUsername(String username);

    User findByUserUsername(String name);
}
