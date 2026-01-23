package com.example.management_system.company;
import java.util.UUID;

import javax.security.sasl.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.management_system.company.DTO.AuthCompanyDTO;
import com.example.management_system.company.DTO.CreateJobDTO;
import com.example.management_system.company.Services.CompanyService;
import com.example.management_system.company.Services.JobService;
import com.example.management_system.company.entities.CompanyEntity;
import com.example.management_system.company.entities.JobEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private JobService jobService;



    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity company) {
        try {
            var result = this.companyService.create(company);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/job")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO job, HttpServletRequest request) {
        try {
            var id = request.getAttribute("token");
            var jobEntity = JobEntity.builder().benefits(job.getBenefit()).level(job.getLevel()).description(job.getDescrpition()).companyCnpj(id.toString()).build();

            var result = this.jobService.create(jobEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public String findAll() {
        return "test";
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody  AuthCompanyDTO authCompanyDTO) {
        try{
            var result = this.companyService.auth(authCompanyDTO);
            return ResponseEntity.ok().body(result);
        }catch( UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }
}
