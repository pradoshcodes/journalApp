package com.vigor.journalApp.Controller;

import com.vigor.journalApp.Entity.User;
import com.vigor.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?>updateUser(@RequestBody User user,@PathVariable  String userName){
        User userInOb=userService.findByUserName(userName);
        if(userInOb !=null){
            userInOb.setUserName(user.getUserName());
            userInOb.setPassword(user.getPassword());
            userService.saveEntry(userInOb);
    }
   return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
}

