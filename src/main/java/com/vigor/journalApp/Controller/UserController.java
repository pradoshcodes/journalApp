package com.vigor.journalApp.Controller;

import com.vigor.journalApp.Entity.JournalEntry;
import com.vigor.journalApp.Entity.User;
import com.vigor.journalApp.Service.JournalService;
import com.vigor.journalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody Users user){
        userService.saveEntry(user);
    }

    @PutMapping
    public ResponseEntity<?>updateUser(@RequestBody User user){
        User userInOb=userService.findByUserName(user.getUserName());
        if(userInOb !=null){
            userInOb.setUserName(user.getUserName());
            userInOb.setPassword(user.getPassword());
            userService.saveEntry(userInOb);
    }
   return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
}

