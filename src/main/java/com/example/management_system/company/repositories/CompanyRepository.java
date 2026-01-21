package com.example.management_system.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.management_system.company.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByCNPJOrNameOrEmail(String cnpj, String name, String email);

    Optional<CompanyEntity> findByCNPJ(String cnpj);
}
