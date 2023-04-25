package com.talentica.copilot.util;

import com.talentica.copilot.dto.ExtendedUserDetails;
import com.talentica.copilot.exception.UnAuthenticatedException;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

  private SecurityUtil() {
  }

  public static String geUserEmail() {
    boolean authenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    if (authenticated) {
      return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    throw new UnAuthenticatedException("User is not authenticated");
  }

  public static ExtendedUserDetails getUser() {
    boolean authenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    if (authenticated) {
      return (ExtendedUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    throw new UnAuthenticatedException("User is not authenticated");
  }

  public static Long getUserId() {
    return getUser().getId();
  }

}
