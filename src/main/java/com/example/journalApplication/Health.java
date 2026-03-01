package com.example.journalApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {
   @GetMapping("health")
    public String getHealth(){
        return "it is ok I am here";
    }
}
