package com.talentica.copilot.dto;

import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
public class ExtendedUserDetails extends User {

  private final Long id;

  public ExtendedUserDetails(Long id, String username, String password, boolean enabled,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, enabled, true, true, true, authorities);
    this.id = id;
  }
}
