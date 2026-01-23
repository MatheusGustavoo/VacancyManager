package com.example.management_system.company.Services;
import java.time.Duration;
import java.time.Instant;
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
    @Value("${security.token.secret}")
    private String key;

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;


  public CompanyEntity create(CompanyEntity company) {
    try {
      repository.findByCnpj(company.getCnpj())
          .ifPresent(c -> { throw new RuntimeException("CNPJ já cadastrado"); });

      company.setPassword(passwordEncoder.encode(company.getPassword()));
      return repository.save(company);

    } catch (Exception ex) {
      throw new RuntimeException("Erro ao salvar (root cause): " + rootCauseMessage(ex), ex);
    }
  }

  private String rootCauseMessage(Throwable t) {
    Throwable cur = t;
    while (cur.getCause() != null && cur.getCause() != cur) cur = cur.getCause();
    return cur.getClass().getName() + ": " + cur.getMessage();
  }



 public String auth(AuthCompanyDTO dto) {
    System.out.println(dto.getCnpj() + " " + dto.getPassword());
    var company = repository.findByCnpj(dto.getCnpj())
        .orElseThrow(() -> new UsernameNotFoundException("Empresa nao encontrada"));

    if (!passwordEncoder.matches(dto.getPassword(), company.getPassword())) {
        throw new org.springframework.security.authentication.BadCredentialsException("Credenciais inválidas");
    }

    var algorithm = Algorithm.HMAC256(key); 
    return JWT.create()
        .withIssuer("ola")
        .withSubject(company.getCnpj().toString())
        .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
        .sign(algorithm);
  }

}
