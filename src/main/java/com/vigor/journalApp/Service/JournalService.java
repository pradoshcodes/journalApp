package com.vigor.journalApp.Service;

import com.vigor.journalApp.Entity.JournalEntry;
import com.vigor.journalApp.Entity.User;
import com.vigor.journalApp.Repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;


    public void saveEntry(JournalEntry journalEntry, String userName, UserService userService){
        User user=userService.findByUserName(userName);
        JournalEntry saved=journalRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalRepository.findById(id);
    }

    public void deleteById(ObjectId id){
         journalRepository.deleteById(id);
    }
}
