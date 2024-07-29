package com.vigor.journalApp.Repository;

import com.vigor.journalApp.Entity.JournalEntry;
import com.vigor.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

}
