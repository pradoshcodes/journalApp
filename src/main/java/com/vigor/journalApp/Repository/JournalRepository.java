package com.vigor.journalApp.Repository;

import com.vigor.journalApp.Entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<JournalEntry, Integer> {

}
