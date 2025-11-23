package com.pinterest.clone.dto;

import lombok.Data;
import java.util.List;

@Data
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String email;
  private String fullName;
  private String profilePic;
  private List<String> roles;

  public JwtResponse(String accessToken, Long id, String email, String fullName, String profilePic, List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.email = email;
    this.fullName = fullName;
    this.profilePic = profilePic;
    this.roles = roles;
  }
}
