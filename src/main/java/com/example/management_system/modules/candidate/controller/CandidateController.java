package com.example.management_system.modules.candidate.controller;
import org.springframework.web.bind.annotation.RestController;

import com.example.management_system.modules.candidate.Candidate;
import com.example.management_system.modules.candidate.CandidateRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/candidate")
public class CandidateController {
    
    @Autowired
    private CandidateRepository repository;

    @PostMapping("/")
    public Candidate create(@Valid @RequestBody Candidate candidate){ 
        this.repository.findByCPFOrEmail(candidate.getCPF(), candidate.getEmail())
        return this.repository.save(candidate);
    }

    @GetMapping("/")
    public String findAll(){
        return "test";
    }
}