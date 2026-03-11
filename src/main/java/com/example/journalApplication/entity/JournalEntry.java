package com.example.journalApplication.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "journalDB")
@Data
public class JournalEntry {
    @Id
    private ObjectId id;
    private String title;
    private String content;

}
