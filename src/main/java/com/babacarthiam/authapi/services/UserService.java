package com.babacarthiam.authapi.services;

import com.babacarthiam.authapi.entities.User;
import com.babacarthiam.authapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAll() {
    return new ArrayList<>(userRepository.findAll());
  }
}
