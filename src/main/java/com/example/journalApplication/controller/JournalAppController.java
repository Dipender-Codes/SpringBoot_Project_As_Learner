package com.example.journalApplication.controller;

import com.example.journalApplication.entity.JournalEntry;
import com.example.journalApplication.service.JournalAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journalApp")
public class JournalAppController {
    @Autowired
    private JournalAppService journalAppService;
@PostMapping
public boolean createEntry(@RequestBody JournalEntry myEntry){
    journalAppService.saveEntryToTheDatabase(myEntry);
    return true;
}

@GetMapping
    public List<JournalEntry> getAllEntries(){
    return journalAppService.getAllEntries();
}

@GetMapping("id/{myId}")
    public Optional<JournalEntry> getEntryByid(@PathVariable Long myId){
    return journalAppService.getEntryId(myId);
}

@DeleteMapping("id/{myId}")
    public void deleteEntryById(@PathVariable Long myId){
    journalAppService.deleteEntryById(myId);
}


@PutMapping("id/{myId}")
    public JournalEntry updateById(@PathVariable Long myId, @RequestBody JournalEntry myjournal) {
    JournalEntry oldEntry = journalAppService.getEntryId(myId).orElse(null);
    if (oldEntry != null && !myjournal.getTitle().isEmpty()) {
        oldEntry.setTitle(myjournal.getTitle());
    }
    if (oldEntry != null && !myjournal.getContent().isEmpty()) {
    oldEntry.setContent(myjournal.getContent());
    }
    return journalAppService.saveEntryToTheDatabase(oldEntry);
}
}
