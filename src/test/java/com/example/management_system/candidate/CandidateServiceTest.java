package com.example.management_system.candidate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.management_system.candidate.entities.AppliedJobsEntity;
import com.example.management_system.candidate.entities.CandidateEntity;
import com.example.management_system.company.entities.JobEntity;
import com.example.management_system.company.repositories.JobRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.Optional;
import java.util.UUID;

public class CandidateServiceTest {

    @InjectMocks
    private CandidateService candidateService;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Test
    @DisplayName("candidate_not_found")
    public void candidate_not_found() {
        try {
            candidateService.newApply(null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UsernameNotFoundException.class);
        }
    }

    @Test
    public void job_not_found() {
        
        
        var candidate = new CandidateEntity();
        candidate.setCpf("1321321");
        when(candidateRepository.findByName("")).thenReturn(Optional.of(candidate));
        try {
            candidateService.newApply(null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UsernameNotFoundException.class);
        }
    }

    @Test
    public void candidate_can_not_apply_job() {
        var cpf = "123123123";
        var idJob = UUID.randomUUID();

        var applyJob = AppliedJobsEntity.builder().idCandidate(cpf).idJob(idJob).build();

        when(candidateRepository.findByCpf(cpf)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity())); 
        
        var idOperation = candidateService.newApply(applyJob);

        assertThat(idOperation).isNotNull();
        assertThat(idOperation).hasFieldOrProperty("id");
    }         
}
