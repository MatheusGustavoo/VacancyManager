package com.example.management_system.candidate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.management_system.applications.AppliedJobRepository;
import com.example.management_system.applications.AppliedJobService;
import com.example.management_system.applications.AppliedJobsEntity;
import com.example.management_system.candidate.entities.CandidateEntity;
import com.example.management_system.company.entities.JobEntity;
import com.example.management_system.company.repositories.JobRepository;

import jakarta.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CandidateServiceTest {

    @InjectMocks
    private CandidateService candidateService;

    @InjectMocks
    private AppliedJobService appliedJobService;

    @Mock
    private AppliedJobRepository appliedJobRepository;

    @Mock
    private JobRepository jobRepository;


    @Mock
    private CandidateRepository candidateRepository;
    @Mock
    private AppliedJobsEntity appliedJobsEntity;

    @Test
    @DisplayName("candidate_not_found")
    public void candidate_not_found() {
        try {
            appliedJobService.newApply("0101010010101", UUID.randomUUID());
        } catch (Exception e) {
            assertThat(e).isInstanceOf(EntityNotFoundException.class);
        }
    }

    @Test
    public void job_not_found() {
        var candidate = new CandidateEntity();
        candidate.setCpf("1321321");
        when(candidateRepository.findByCpf("")).thenReturn(Optional.of(candidate));
        try {
            appliedJobService.newApply("", UUID.randomUUID());
        } catch (Exception e) {
            assertThat(e).isInstanceOf(EntityNotFoundException.class);
            assertThat(e.getMessage()).isEqualTo("Id do Job não encontrado na nossa base de dados");
        }
    }

     
}
