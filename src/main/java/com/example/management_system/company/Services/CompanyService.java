package com.example.management_system.company.Services;

import java.time.Duration;
import java.time.Instant;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.management_system.company.DTO.AuthCompanyDTO;
import com.example.management_system.company.entities.CompanyEntity;
import com.example.management_system.company.repositories.CompanyRepository;

@Service
public class CompanyService {
    @Value("${security.toke.secret}")
    private String key;

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity create(CompanyEntity company) {
        this.repository.findByCNPJOrNameOrEmail(company.getName(), company.getName(), company.getEmail())
                .ifPresent(user -> {
                    throw new RuntimeException("Company already exists");
                });

        var password = passwordEncoder.encode(company.getPassword());
        company.setPassword(password);

        return this.repository.save(company);
    }

    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.repository.findByCNPJ(authCompanyDTO.getCNPJ()).orElseThrow(() -> {
                    throw new UsernameNotFoundException("Company not found");
                }
        );
        var match = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if(!match){
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(key);
        var token = JWT.create().withIssuer("").withExpiresAt(Instant.now().plus(Duration.ofHours(2))).withSubject(company.getCNPJ().toString()).sign(algorithm);

        return token;
    }

}
