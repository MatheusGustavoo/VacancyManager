package com.example.management_system.company.entities;

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
@Entity(name = "company")
public class CompanyEntity {

    @Id
    @Column(name = "cnpj", nullable = false, length = 14)
    @NotBlank(message = "Preencha o CNPJ")
    private String cnpj;

    @NotBlank(message = "O nome é obrigatório")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "O nome deve conter apenas letras e espaços")
    private String name;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String password;

    private String website;
    private String description;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
