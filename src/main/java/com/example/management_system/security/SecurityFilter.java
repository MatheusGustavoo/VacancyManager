package com.example.management_system.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.val;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    // @Override
    @Value("${security.token.secret}")
    private String key;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        SecurityContextHolder.getContext().setAuthentication(null);

        String header = request.getHeader("Authorization");
        if (header != null) {
            var subjectToken = this.validadeToken(header);
            if (subjectToken.isEmpty()) {
                response.setStatus((HttpServletResponse.SC_UNAUTHORIZED));
                return;
            }
            request.setAttribute("company", subjectToken);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(subjectToken, null,
                    Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        // throw new UnsupportedOperationException("Unimplemented method
        // 'doFilterInternal'");
        System.out.println(header);
        filterChain.doFilter(request, response);
    }

    public String validadeToken(String token) {
        token = token.replace("Bearer ", "");

        var algorithm = Algorithm.HMAC256(key); // garantir que key não é null

        try {

            return JWT.require(algorithm).build().verify(token).getSubject();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return "";
        }
    }
}
