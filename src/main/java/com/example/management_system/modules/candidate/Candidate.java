package com.example.management_system.modules.candidate;

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
@Entity(name = "candidate")
public class Candidate {
    
    @Id
    @Pattern(regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})", message = "CPF inválido. Digite um CPF no formato: 'xxx.xxx.xxx-xx' ou 'xxx.xxx.xxx/xxxx-xx'")
    @NotBlank
    private String CPF;
    
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "O nome deve conter apenas letras e espaços")
    private String name;
    
    @Email(message = "Email inválido. Digite um email válido no formato: 'nome@domínio.com'")
    private String email;

    @Length(min = 8, max = 16, message = "A senha deve ter entre 8 e 16 caracteres")
    private String password;
    private String description;
    private String resume;

    @CreationTimestamp
    private LocalDateTime createdAt;
}