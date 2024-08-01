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

import java.util.*;

@RestController
@RequestMapping("/journal")
public class journalControllerV2 {

    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
        User user=userService.findByUserName(userName);
        List<JournalEntry> all= user.getJournalEntries();
        if(all !=null && !all.isEmpty()){
            return  new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntity(@RequestBody JournalEntry myEntry,@PathVariable String userName) {
      try {
          journalService.saveEntry(myEntry,userName);
          return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
      }catch (Exception e){
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getEntryId(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalService.findById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteEntryId(@PathVariable ObjectId myId,@PathVariable String userName) {
        journalService.deleteById(myId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> updateEntryId(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
        JournalEntry old = journalService.findById(myId).orElse(null);
        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            journalService.saveEntry(old);
            return  new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}



