package com.example.management_system.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.management_system.company.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
    Optional<JobEntity> findById(UUID id);
}
