package com.example.management_system.candidate;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidate")
public class CandidateEntity {
    
    @Id
    private String cpf;
    
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "O nome deve conter apenas letras e espaços")
    private String name;
    

    @NotBlank
    @Email(message = "Email inválido. Digite um email válido no formato: 'nome@domínio.com'")
    private String email;

    @NotBlank
    private String password;
    private String description;
    private String resume;

    @CreationTimestamp
    private LocalDateTime createdAt;
}