package com.example.journalApplication.service;

import com.example.journalApplication.entity.JournalEntry;
import com.example.journalApplication.entity.UserEntry;
import com.example.journalApplication.repository.JournalAppRepository;
import com.example.journalApplication.repository.UserRepository;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/* Logic of my Code will be written here it is the best practice for a developer to make hierachy
like Controller where end point is written will call service where business logic is written and it
will call Repository to get the CRUD operations from MongoRepository which will be paired to
Database and entity
*/
@RestController
public class UserService {
    @Autowired
    private UserRepository userRepository;

public UserEntry saveEntryToTheDatabase(UserEntry userEntry){
    return userRepository.save(userEntry);
}

public List<UserEntry> getAllEntries(){
    return userRepository.findAll();
}

public Optional<UserEntry> getEntryId(ObjectId myId){
    return userRepository.findById(myId);
}

public void deleteEntryById(ObjectId myId){
   userRepository.deleteById(myId);
}

public UserEntry findByUserName(String userName){
    return userRepository.findByUserName(userName);
}
}
