package com.talentica.copilot.service;

import com.talentica.copilot.dto.CreateUserRequest;
import com.talentica.copilot.dto.UserDto;
import com.talentica.copilot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  UserDto getUserById(Long id);

  UserDto createUser(CreateUserRequest request);

  User getLoggedInUser();
}
