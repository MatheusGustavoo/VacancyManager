package com.example.management_system.candidate;

import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.management_system.applications.AppliedJobsEntity;
import com.example.management_system.candidate.entities.CandidateEntity;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret.candidate}")
    private String key;

    public CandidateEntity create(CandidateEntity candidate) throws SQLException{

        if (repository.findByCpf(candidate.getCpf()).isPresent()) {
            throw new RuntimeException("CPF já existe");
        }

        if (repository.findByEmail(candidate.getEmail()).isPresent()) {
            throw new RuntimeException("Email já existe");
        }
        candidate.setPassword(passwordEncoder.encode(candidate.getPassword()));

        return repository.save(candidate);
    }

    public String auth(AuthCandidateDTO dto) {
        var candidate = this.repository.findByCpf(dto.cpf()).orElseThrow(() -> {
            throw new UsernameNotFoundException("CPF inválido");
        });

        var match = this.passwordEncoder.matches(dto.password(), candidate.getPassword());

        if (!match) {
            throw new BadCredentialsException("Credencial inválida");
        }

        Algorithm algorithm = Algorithm.HMAC256(key);

        return JWT.create().withIssuer("ola").withSubject(candidate.getCpf())
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2))).sign(algorithm);
    }

    public ProfileCandidateDTO execute(String name) {
        System.out.println(name);
        var candidate = this.repository.findByCpf(name).orElseThrow(() -> {
            throw new UsernameNotFoundException("CPF inválido");
        });

        var res = ProfileCandidateDTO.builder().name(candidate.getName()).email(candidate.getEmail()).description(candidate.getDescription()).resume(candidate.getResume()).build();

        return res;
    }

}
