package com.vigor.journalApp.Service;

import com.vigor.journalApp.Entity.JournalEntry;
import com.vigor.journalApp.Repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;


    public void saveEntry(JournalEntry journalEntry){
        journalRepository.save(journalEntry);
    }
}
