package com.example.management_system.company.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobDTO {
    private String benefit;
    private String level;
    private String descrpition;
}
