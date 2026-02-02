package com.example.management_system.applications;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @NotNull(message = "O id do job é obrigatório")
    private UUID idJob;

    @NotBlank(message = "O nome do candidato é obrigatório")
    private String nameCandidate;
}
