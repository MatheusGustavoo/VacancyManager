package com.example.management_system.company.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.management_system.company.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, String> {
    Optional<CompanyEntity> findByCnpjOrNameOrEmail(String cnpj, String name, String email);
    Optional<CompanyEntity> findByCnpj(String cnpj);
}
