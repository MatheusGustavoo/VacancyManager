package com.example.management_system.company.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.management_system.company.entities.CompanyEntity;

public interface CompanyReposito extends JpaRepository<CompanyEntity, UUID> {
    
}
