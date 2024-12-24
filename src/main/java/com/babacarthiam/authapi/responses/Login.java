package com.babacarthiam.authapi.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Login {
  private String token;
  private long expiresIn;
}
