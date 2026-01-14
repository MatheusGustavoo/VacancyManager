package com.example.management_system.modules.candidate.controller;
import org.springframework.web.bind.annotation.RestController;

import com.example.management_system.modules.candidate.Candidate;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/candidate")
public class CandidateController {
    
    @PostMapping("/")
    public void candidate(@Valid @RequestBody Candidate candidate){ 
        
    }
}