package com.example.first_project.Security.auth;

import com.example.first_project.Security.config.JwtService;
import com.example.first_project.model.Role;
import com.example.first_project.model.Students;
import com.example.first_project.repository.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final StudentsRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    public AuthenticationResponce register(RegisterRequest request){

        var students = Students.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode((request.getPassword())))
                .role(Role.USER)
                .build();
        repository.save(students);
        var jwtToken = jwtService.generateToken(students);
        return AuthenticationResponce.builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponce Authentication(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var students = repository.findByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken((UserDetails) students);
        return AuthenticationResponce.builder()
                .token(jwtToken)
                .build();

    }
}
