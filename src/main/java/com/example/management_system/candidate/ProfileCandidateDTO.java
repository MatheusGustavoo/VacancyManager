package com.example.management_system.candidate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileCandidateDTO {
    private String name;
    private String email;
    private String description;
    private String resume;
}
