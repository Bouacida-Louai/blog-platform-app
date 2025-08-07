package org.louai.blogapp.controller;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SQLRestriction;
import org.louai.blogapp.domain.dtos.AuthResponse;
import org.louai.blogapp.domain.dtos.LoginRequest;
import org.louai.blogapp.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private AuthenticationService  authenticationService;

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
      UserDetails   userDetails= authenticationService.authenticate
              (loginRequest.getEmail(), loginRequest.getPassword());

      String tokenValue = authenticationService.generateToken(userDetails);
       AuthResponse authResponse= AuthResponse.builder()
              .token(tokenValue)
              .expiresIn(86400)
              .build();
      return  ResponseEntity.ok(authResponse);
    }
}
