package com.vigor.journalApp.Controller;

import com.vigor.journalApp.Entity.JournalEntry;
import com.vigor.journalApp.Service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class journalControllerV2 {

    @Autowired
    private JournalService journalService;


    @GetMapping
    public ResponseEntity<?> getAll() {
        List<JournalEntry> all= journalService.getAll();
        if(all !=null && !all.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntity(@RequestBody JournalEntry myEntry) {
      try {
          myEntry.setDate(LocalDateTime.now());
          journalService.saveEntry(myEntry);
          return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
      }catch (Exception e){
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getEntryId(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalService.findById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.Ok);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteEntryId(@PathVariable ObjectId myId) {
        journalService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateEntryId(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        JournalEntry old = journalService.findById(id).orElse(null);
        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            journalService.saveEntry(old);
            return  new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}



