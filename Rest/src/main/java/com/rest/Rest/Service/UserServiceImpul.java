package com.rest.Rest.Service;

import com.rest.Rest.Entity.User;
import com.rest.Rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class UserServiceImpul implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> findAll() {
       return userRepo.findAll();
    }

    @Override
    public User UpdateByUserName(String username,User userRequest) {
        System.out.println(username);
        User user = userRepo.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User notfound");
        }
         user.setPassword(userRequest.getPassword());
        user.setUsername(userRequest.getUsername());
        user.setPhone(userRequest.getPhone());
        user.setEmail(userRequest.getEmail());
        user.setAddress(userRequest.getAddress());
        userRepo.save(user);
        return user;
    }

    @Override
    public User findByUserId(Long id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User notfound using this id");
        }
        return user.get();
    }

    @Override
    public String SaveUser(User user) {
        User user2 = userRepo.save(user);
        return "User save successfully";
    }



    @Override
    public boolean DeleteUserByUsername(String username) {
        User user = findByUserUsername(username);

        try {
            userRepo.delete(user);
        }catch (Exception e){

            return false;
        }

return true;
    }

    @Override
    public User findByUserUsername(String name) {
        User user = userRepo.findByUsername(name);
        if(user==null){
            throw new UsernameNotFoundException("User not found using this id");
        }
        return user;
    }

}
