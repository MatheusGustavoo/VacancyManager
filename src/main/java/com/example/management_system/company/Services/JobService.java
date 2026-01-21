package com.example.management_system.company.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.management_system.company.entities.JobEntity;
import com.example.management_system.company.repositories.JobRepository;

@Service
public class JobService {

    @Autowired
    private JobRepository repository;

    public JobEntity create(JobEntity job) {
        return this.repository.save(job);
    }    
}
