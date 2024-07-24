package com.vigor.journalApp.Controller;

import com.vigor.journalApp.Entity.JournalEntry;
import com.vigor.journalApp.Service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public List<JournalEntry> getAll() {
        return journalService.getAll();
    }

    @PostMapping
    public JournalEntry createEntity(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        journalService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getEntryId(@PathVariable ObjectId myId) {
        return journalService.findById(myId).orElse(null);

    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntryId(@PathVariable ObjectId myId) {
        journalService.deleteById(myId);
        return true;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateEntryId(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        JournalEntry old = journalService.findById(id).orElse(null);
        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
        }
        journalService.saveEntry(old);
        return  old;
    }
}



