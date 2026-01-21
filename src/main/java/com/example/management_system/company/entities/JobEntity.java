package com.example.management_system.company.entities;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity(name = "job")
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Este item é obrigatório")
    private String level;
    private String benefits;
    private String requirements;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_CNPJ", // coluna na tabela job
            referencedColumnName = "CNPJ", // PK na tabela company
            nullable = false, foreignKey = @ForeignKey(name = "fk_job_company_CNPJ"))
    private CompanyEntity company;

    @CreationTimestamp
    private LocalDateTime createdAt;
}