package com.example.management_system.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.management_system.company.entities.CompanyEntity;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService CompanyService;

    @RequestMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity company) {
        try {
            var result = this.CompanyService.create(company);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping("/")
    public String findAll() {
        return "test";
    }
}
