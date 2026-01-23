package com.example.management_system.candidate;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService CandidateService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        try {
            var result = this.CandidateService.create(candidate);
            return ResponseEntity.ok().body(result);}
     
        catch (Exception e) {
            // e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody  AuthCandidateDTO authCandidateDTO) {
        try{
            var result = this.CandidateService.auth(authCandidateDTO);
            return ResponseEntity.ok().body(result);
        }catch( UsernameNotFoundException | BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        try {
        var nameCandidate = request.getAttribute("token");
        var candidateProfile = this.CandidateService.execute(nameCandidate.toString()); 
        return ResponseEntity.ok().body(candidateProfile);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}