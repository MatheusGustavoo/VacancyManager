package com.example.management_system.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.management_system.company.entities.CompanyEntity;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository repository;

    
    public CompanyEntity  create(CompanyEntity company) {
        this.repository.findByCNPJOrNameOrEmail(company.getName(), company.getName(), company.getEmail()).ifPresent(user->{
            throw new RuntimeException("Company already exists");
        });
        
         
        return this.repository.save(company);
    }
}
