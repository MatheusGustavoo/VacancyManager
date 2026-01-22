package com.example.management_system.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository repository;

    public CandidateEntity create(CandidateEntity candidate) {

    if (repository.findByCPF(candidate.getCPF()).isPresent()) {
        throw new RuntimeException("CPF já existe");
    }

    if (repository.findByEmail(candidate.getEmail()).isPresent()) {
        throw new RuntimeException("Email já existe");
    }

    return repository.save(candidate);
}

}
