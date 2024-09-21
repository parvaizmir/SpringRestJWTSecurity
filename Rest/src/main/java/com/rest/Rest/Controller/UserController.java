package com.rest.Rest.Controller;

import com.rest.Rest.Entity.User;
import com.rest.Rest.Service.JWTService;
import com.rest.Rest.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
   private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public String SaveUser(@RequestBody User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        String save=userService.SaveUser(user);
        return save;
    }



    @GetMapping("/")
    public List<User> getUsers(){
       return userService.findAll();
    }

    @GetMapping("/{id}")
   public User findById(@PathVariable("id") Long id){
      User user=  userService.findByUserId(id);
      return user;
   }

   @PutMapping("/{username}")
   public User updateUser(@RequestBody User user,@PathVariable("username") String username){
      user.setPassword(bCryptPasswordEncoder.encode(user.getUsername()));
       User user1 = userService.UpdateByUserName(username, user);
  return user1;
   }

    @DeleteMapping("/{username}")
    public boolean DeleteUserByusername(@PathVariable("username") String username){
       boolean delete = userService.DeleteUserByUsername(username);
        return delete;
    }

    @GetMapping("/user")
    public User findByUserUsername(@RequestParam("name") String name){
        User user=  userService.findByUserUsername(name);
        return user;
    }

    @GetMapping("/login")
    public String checkLogin(@RequestParam("username") String username,@RequestParam("password") String password){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
     if( authenticate.isAuthenticated()){
 return jwtService.generateToken(username);
     }else{
         return "Fail";
     }

    }
}
