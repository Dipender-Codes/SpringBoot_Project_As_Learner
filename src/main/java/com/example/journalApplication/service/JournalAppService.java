package com.example.journalApplication.service;

import com.example.journalApplication.entity.JournalEntry;
import com.example.journalApplication.entity.UserEntry;
import com.example.journalApplication.repository.JournalAppRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private UserService userService;

@Transactional
public void saveEntryToTheDatabase(JournalEntry journalEntry, String userName){
    UserEntry user= userService.findByUserName(userName);
    JournalEntry saved= journalAppRepository.save(journalEntry);
    user.getUserJournals().add(saved);
    userService.saveEntryToTheDatabase(user);
}

public void saveEntryToTheDatabase(JournalEntry journalEntry){
        journalAppRepository.save(journalEntry);
}

    public List<JournalEntry> getAllEntries(){
    return journalAppRepository.findAll();
}

public Optional<JournalEntry> getEntryId(ObjectId myId){
    return journalAppRepository.findById(myId);
}

@Transactional
public void deleteEntryById(ObjectId myId, String userName)
{
    UserEntry user = userService.findByUserName(userName);
    user.getUserJournals().removeIf(x-> x.getId().equals(myId));
    userService.saveEntryToTheDatabase(user);
    journalAppRepository.deleteById(myId);
}
}
