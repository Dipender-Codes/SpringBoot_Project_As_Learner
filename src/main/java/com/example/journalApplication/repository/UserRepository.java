package com.example.journalApplication.repository;

import com.example.journalApplication.entity.JournalEntry;
import com.example.journalApplication.entity.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntry, ObjectId> {
UserEntry findByUserName(String username);
}
