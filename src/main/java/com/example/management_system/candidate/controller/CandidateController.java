package com.example.management_system.candidate.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.management_system.candidate.CandidateEntity;
import com.example.management_system.candidate.CandidateRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository repository;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        if (!this.repository.findByCPFOrEmail(candidate.getCPF(), candidate.getEmail()).isEmpty()) {
            this.repository.save(candidate);
            return ResponseEntity.ok().body(candidate);
        } else {
            return ResponseEntity.badRequest().body("CPF ou Email já cadastrado");
        }

    }

    @GetMapping("/")
    public String findAll() {
        return "test";
    }
}