package com.example.management_system.modules.candidate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Candidate {
    private String CPF;
    
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "O nome deve conter apenas letras e espaços")
    private String name;
    
    @Email(message = "Email inválido. Digite um email válido no formato: 'nome@domínio.com'")
    private String email;

    @Length(min = 8, max = 16, message = "A senha deve ter entre 8 e 16 caracteres")
    private String password;
    private String description;
    private String resume;

}