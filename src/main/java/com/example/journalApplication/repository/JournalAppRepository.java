package com.example.journalApplication.repository;

import com.example.journalApplication.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalAppRepository extends MongoRepository<JournalEntry, Long> {

}
