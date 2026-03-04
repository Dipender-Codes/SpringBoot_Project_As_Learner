package com.example.journalApplication.service;

import com.example.journalApplication.entity.JournalEntry;
import com.example.journalApplication.repository.JournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/* Logic of my Code will be written here it is the best practice for a developer to make hierachy
like Controller where end point is written will call service where business logic is written and it
will call Repository to get the CRUD operations from MongoRepository which will be paired to
Database and entity
*/
@RestController
public class JournalAppService {
    @Autowired
    private JournalAppRepository journalAppRepository;

public JournalEntry saveEntryToTheDatabase(JournalEntry journalEntry){
    return journalAppRepository.save(journalEntry);
}

public List<JournalEntry> getAllEntries(){
    return journalAppRepository.findAll();
}

public Optional<JournalEntry> getEntryId(Long myId){
    return journalAppRepository.findById(myId);
}

public void deleteEntryById(Long myId){
   journalAppRepository.deleteById(myId);
}

}
