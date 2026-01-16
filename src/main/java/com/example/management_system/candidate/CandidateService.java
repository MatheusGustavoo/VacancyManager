package com.example.management_system.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository repository;

    public CandidateEntity create(CandidateEntity candidate) {
        this.repository.findByCPFOrEmail(candidate.getCPF(), candidate.getEmail()).ifPresent(user->{
            throw new RuntimeException("Email ou CPF ja existe");
        });
        return this.repository.save(candidate);
    }
}
