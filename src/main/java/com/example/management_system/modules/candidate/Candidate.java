package com.example.management_system.modules.candidate;

import lombok.Data;

@Data
public class Candidate {
    private String CPF;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String description;
    private String resume;

}