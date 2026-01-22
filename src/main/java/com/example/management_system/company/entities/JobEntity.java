package com.example.management_system.company.entities;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "job")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Este item é obrigatório")
    private String level;
    private String benefits;
    private String requirements;
    private String description;

    @ManyToOne()
    @JoinColumn(name = "company_cnpj", insertable=false, updatable = false)
    private CompanyEntity companyEntity;

    @Column(name = "company_cnpj")
    private String companyCnpj;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
