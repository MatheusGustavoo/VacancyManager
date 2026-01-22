package com.example.management_system.candidate;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity, String> {
    Optional<CandidateEntity> findByCPF(String cpf);
    Optional<CandidateEntity> findByEmail(String email);

}
