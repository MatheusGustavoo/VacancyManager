package com.example.management_system.company.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "company")
public class CompanyEntity {
    @Id
    private String CNPJ;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "O nome deve conter apenas letras e espaços")
    private String name;  
    
    @Email(message = "Email inválido. Digite um email válido no formato: 'nome@domínio.com'")
    private String email;

    @Length(min = 8, max = 16, message = "A senha deve ter entre 8 e 16 caracteres")
    private String password;

    private String website;   
    private String description;   
    
    @CreationTimestamp
    private LocalDateTime createdAt;   
}
