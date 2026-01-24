package com.example.management_system.candidate;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.management_system.candidate.entities.CandidateEntity;

public interface CandidateRepository extends JpaRepository<CandidateEntity, String> {
    Optional<CandidateEntity> findByCpf(String cpf);
    Optional<CandidateEntity> findByEmail(String email);

    Optional<CandidateEntity> findByName(String name);

}
