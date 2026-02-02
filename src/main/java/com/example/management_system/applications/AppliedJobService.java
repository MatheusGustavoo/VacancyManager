package com.example.management_system.applications;

import java.net.http.HttpClient;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.management_system.candidate.CandidateRepository;
import com.example.management_system.company.repositories.CompanyRepository;
import com.example.management_system.company.repositories.JobRepository;

@Service
public class AppliedJobService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private AppliedJobRepository appliedJobRepository;

    public AppliedJobsEntity newApply(String userName, UUID idJob) {
        var job = jobRepository.findById(idJob).orElseThrow(() -> new UsernameNotFoundException("Id do Job não encontrado na nossa base de dados"));

        var candidate = candidateRepository.findByName(userName).orElseThrow(() -> new UsernameNotFoundException("Nome do usuário inválido"));

        this.appliedJobRepository.findByIdJobAndNameCandidate(idJob, userName).ifPresent(app -> {
            throw new RuntimeException("Candidato ja aplicou para essa vaga");
        });

        try {
            AppliedJobsEntity application = AppliedJobsEntity.builder().nameCandidate(candidate.getName()).idJob(job.getId()).build();
            return appliedJobRepository.save(application);

        } catch (Exception e) {
            e.printStackTrace();

            throw new RuntimeException(e.getMessage());
        }
    }
}
