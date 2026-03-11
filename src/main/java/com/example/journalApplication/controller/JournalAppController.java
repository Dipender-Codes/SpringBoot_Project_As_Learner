package com.example.journalApplication.controller;

import com.example.journalApplication.entity.JournalEntry;
import com.example.journalApplication.entity.UserEntry;
import com.example.journalApplication.service.JournalAppService;
import com.example.journalApplication.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.JobHoldUntil;
import java.util.*;

@RestController
@RequestMapping("/journalApp")
public class JournalAppController {
    @Autowired
    private JournalAppService journalAppService;

    @Autowired
    private UserService userService;

    @PostMapping("/userName")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName) {
        try {
            journalAppService.saveEntryToTheDatabase(myEntry,userName);
            return new ResponseEntity<JournalEntry>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/userName")
    public ResponseEntity<List<JournalEntry>> getAllEntriesOfUsers(@PathVariable String username) {
        UserEntry user= userService.findByUserName(username);
        List<JournalEntry> listInDatabase = user.getUserJournals();
        if (listInDatabase != null && !listInDatabase.isEmpty()) {
            return new ResponseEntity<>(listInDatabase, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getEntryByid(@PathVariable ObjectId myId) {
        Optional<JournalEntry> myjournal = journalAppService.getEntryId(myId);
        if (myjournal.isPresent() && myjournal != null) {
            return new ResponseEntity<>(myjournal.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<JournalEntry> deleteEntryById(@PathVariable ObjectId myId, @PathVariable String userName) {
        Optional<JournalEntry> myjournal = journalAppService.getEntryId(myId);
        if (myjournal.isPresent()) {
            journalAppService.deleteEntryById(myId, userName);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("id/{myId}")
    public ResponseEntity<JournalEntry> updateById(@PathVariable ObjectId myId, @RequestBody JournalEntry myjournal) {
        JournalEntry oldEntry = journalAppService.getEntryId(myId).orElse(null);
        if (oldEntry != null) {
            if (myjournal.getTitle() != null && !myjournal.getTitle().isEmpty()) {
                oldEntry.setTitle(myjournal.getTitle());
            }
            if (myjournal.getContent() != null && !myjournal.getContent().isEmpty()) {
                oldEntry.setContent(myjournal.getContent());
            }
            journalAppService.saveEntryToTheDatabase(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

