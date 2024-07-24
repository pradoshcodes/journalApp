package com.vigor.journalApp.Controller;

import com.vigor.journalApp.Entity.JournalEntry;
import com.vigor.journalApp.Service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class journalControllerV2 {

    @Autowired
    private JournalService journalService;


    @GetMapping
    public List<JournalEntry> getAll(){
        return null;
    }

    @PostMapping
    public boolean createEntity(@RequestBody JournalEntry myEntry){
    journalService.saveEntry(myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getEntryId(@PathVariable int myId){
        return null;

    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteEntryId(@PathVariable int myId ){
        return null;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateEntryId(@PathVariable int id,@RequestBody JournalEntry myEntry){
        return null;
    }


}
