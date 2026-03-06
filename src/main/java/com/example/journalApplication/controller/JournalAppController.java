package com.example.journalApplication.controller;

import com.example.journalApplication.entity.JournalEntry;
import com.example.journalApplication.service.JournalAppService;
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

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
        try {
            journalAppService.saveEntryToTheDatabase(myEntry);
            return new ResponseEntity<JournalEntry>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAllEntries() {
        List<JournalEntry> listInDatabase = journalAppService.getAllEntries();
        if (listInDatabase != null && !listInDatabase.isEmpty()) {
            return new ResponseEntity<>(listInDatabase, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getEntryByid(@PathVariable Long myId) {
        Optional<JournalEntry> myjournal = journalAppService.getEntryId(myId);
        if (myjournal.isPresent() && myjournal != null) {
            return new ResponseEntity<>(myjournal.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<JournalEntry> deleteEntryById(@PathVariable Long myId) {
        Optional<JournalEntry> myjournal = journalAppService.getEntryId(myId);
        if (myjournal.isPresent()) {
            journalAppService.deleteEntryById(myId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @PutMapping("id/{myId}")
    public ResponseEntity<JournalEntry> updateById(@PathVariable Long myId, @RequestBody JournalEntry myjournal) {
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

