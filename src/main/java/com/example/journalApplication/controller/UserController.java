package com.example.journalApplication.controller;

import com.example.journalApplication.entity.UserEntry;
import com.example.journalApplication.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserEntry> createUsers(@RequestBody UserEntry myEntry) {
     try {
         userService.saveEntryToTheDatabase(myEntry);
         return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
     }
     catch(Exception e){
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
     }
    }

    @GetMapping
    public ResponseEntity<List<UserEntry>> getAllEntries() {
     List<UserEntry> allEntries= userService.getAllEntries();
     if(!allEntries.isEmpty() && allEntries!=null){
         return new ResponseEntity<>(allEntries,HttpStatus.OK);
     }
     else{
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<UserEntry> getEntryByid(@PathVariable ObjectId myId) {
      Optional<UserEntry> userInDB= userService.getEntryId(myId);
      if(userInDB!=null && !userInDB.isEmpty()){
          return new ResponseEntity<>(userInDB.get(),HttpStatus.OK);
      }
      else{
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }
//
//    @DeleteMapping("id/{myId}")
//    public ResponseEntity<JournalEntry> deleteEntryById(@PathVariable Long myId) {
//
//
//    }
//
//
    @PutMapping("/{myUsername}")
    public ResponseEntity<UserEntry> updateByUsername(@PathVariable String myUsername, @RequestBody UserEntry userEntry) {
        UserEntry userInDB = userService.findByUserName(myUsername);
        if(myUsername!=null){
         userInDB.setUserName(userEntry.getUserName());
         userInDB.setPassword(userEntry.getPassword());
         userService.saveEntryToTheDatabase(userInDB);
         return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


