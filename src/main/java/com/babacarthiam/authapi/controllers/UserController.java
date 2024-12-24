package com.babacarthiam.authapi.controllers;

import com.babacarthiam.authapi.entities.User;
import com.babacarthiam.authapi.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/users")
@RestController
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/me")
  public ResponseEntity<User> getCurrentUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) auth.getPrincipal();
    return ResponseEntity.ok(user);
  }

  @GetMapping("/")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.getAll();
    return ResponseEntity.ok(users);
  }
}
