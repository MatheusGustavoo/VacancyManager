package com.example.management_system.applications;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.example.management_system.candidate.entities.CandidateEntity;
import com.example.management_system.company.entities.CompanyEntity;
import com.example.management_system.company.entities.JobEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "applied_jobs")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppliedJobsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @ManyToOne()
    @JoinColumn(name = "id_job", insertable=false, updatable = false)
    private JobEntity job;


    @Column(name = "id_job")
    private UUID idJob;

    @ManyToOne()
    @JoinColumn(name = "id_candidate", insertable=false, updatable = false)
    private CandidateEntity candidate;

    @Column(name = "id_candidate")
    private String idCandidate;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
