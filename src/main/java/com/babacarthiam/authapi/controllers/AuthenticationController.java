package com.babacarthiam.authapi.controllers;

import com.babacarthiam.authapi.dtos.LoginDTO;
import com.babacarthiam.authapi.dtos.RegisterDTO;
import com.babacarthiam.authapi.entities.User;
import com.babacarthiam.authapi.responses.Login;
import com.babacarthiam.authapi.services.AuthenticationService;
import com.babacarthiam.authapi.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
  private final JwtService jwtService;
  private final AuthenticationService authenticationService;

  public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
    this.jwtService = jwtService;
    this.authenticationService = authenticationService;
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody RegisterDTO registerDTO) {
    User user = authenticationService.register(registerDTO);
    return ResponseEntity.ok(user);
  }

  @PostMapping("/login")
  public ResponseEntity<Login> login(@RequestBody LoginDTO loginDTO) {
    User user = authenticationService.login(loginDTO);
    String token = jwtService.generateToken(user);

    Login response = new Login();
    response.setToken(token);
    response.setExpiresIn(jwtService.getExpirationTime());
    return ResponseEntity.ok(response);
  }
}

