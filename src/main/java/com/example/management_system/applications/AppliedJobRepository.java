package com.example.management_system.applications;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppliedJobRepository extends JpaRepository<AppliedJobsEntity, UUID>{
    Optional<AppliedJobsEntity> findByIdJobAndNameCandidate(UUID idJob, String nameCandidate);
} 
