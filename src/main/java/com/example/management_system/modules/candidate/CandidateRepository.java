package com.example.management_system.modules.candidate;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, String> {
    Optional<Candidate> findByCPFOrEmail(String CPF, String email);
}
