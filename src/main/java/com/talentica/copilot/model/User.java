package com.talentica.copilot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// entity for user of a social media app
@Entity(name = "User")
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Builder
public class User extends BaseEntity {

  //Name of the user
  @Column(name = "name", nullable = false, length = 100)
  private String name;

  //Email of the user
  @Column(name = "email", nullable = false, length = 50, unique = true)
  private String email;

  //Password of the user
  @Column(name = "password", nullable = false, length = 64)
  private String password;

  //Bio of the user
  @Column(name = "bio", length = 500)
  private String bio;

  // Is user active
  @Column(name = "is_active", nullable = false)
  private Boolean isActive;

  // Is user verified
  @Column(name = "is_verified", nullable = false)
  private Boolean isVerified;

  // verified at time
  @Column(name = "verified_at")
  private LocalDateTime verifiedAt;

  // deactivated at time
  @Column(name = "deactivated_at")
  private LocalDateTime deactivatedAt;

}
