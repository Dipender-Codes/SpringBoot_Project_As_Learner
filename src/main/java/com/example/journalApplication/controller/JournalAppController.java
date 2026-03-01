package com.example.journalApplication.controller;

import com.example.journalApplication.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journalApp")
public class JournalAppController {
Map<Long,JournalEntry> journalEntry = new HashMap<>();
@PostMapping
public boolean createEntry(@RequestBody JournalEntry myEntry){
    journalEntry.put(myEntry.getId(), myEntry);
    return true;
}

@GetMapping
    public List<JournalEntry> getAllEntries(){
    return new ArrayList<JournalEntry>(journalEntry.values());
}

@GetMapping("id/{myId}")
    public JournalEntry getEntryByid(@PathVariable Long myId){
    return journalEntry.get(myId);
}

@DeleteMapping("id/{myId}")
    public JournalEntry deleteEntryById(@PathVariable Long myId){
    return journalEntry.remove(myId);
}

@PutMapping("id/{myId}")
    public JournalEntry updateById(@PathVariable Long myId, @RequestBody JournalEntry myjournal)
{
    journalEntry.put(myId,myjournal);
    return journalEntry.get(myId);
}
}
