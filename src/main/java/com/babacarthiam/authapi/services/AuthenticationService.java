package com.babacarthiam.authapi.services;

import com.babacarthiam.authapi.dtos.LoginDTO;
import com.babacarthiam.authapi.dtos.RegisterDTO;
import com.babacarthiam.authapi.entities.User;
import com.babacarthiam.authapi.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
  }

  @Transactional
  public User register(RegisterDTO registerDTO) {
    User user = new User();
    user.setName(registerDTO.getName());
    user.setEmail(registerDTO.getEmail());
    user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
    return userRepository.save(user);
  }

  public User login(LoginDTO loginDTO) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
    return userRepository.findUserByEmail(loginDTO.getEmail()).orElseThrow();
  }
}
