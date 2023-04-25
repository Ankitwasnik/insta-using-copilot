package com.talentica.copilot.service;

import com.talentica.copilot.dto.CreateUserRequest;
import com.talentica.copilot.dto.UserDto;

public interface UserService {

  UserDto getUserById(Long id);

  UserDto createUser(CreateUserRequest request);
}
