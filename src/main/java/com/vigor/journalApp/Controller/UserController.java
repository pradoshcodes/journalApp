package com.vigor.journalApp.Controller;

import com.vigor.journalApp.Entity.JournalEntry;
import com.vigor.journalApp.Service.JournalService;
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


    public List<User> getAll

}



