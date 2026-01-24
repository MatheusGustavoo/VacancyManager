package com.example.management_system.candidate.entities;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppliedJobsEntity {
    private UUID idJob;
    private String idCandidate;
}
