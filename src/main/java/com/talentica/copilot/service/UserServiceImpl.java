package com.talentica.copilot.service;

import com.talentica.copilot.dto.CreateUserRequest;
import com.talentica.copilot.dto.UserDto;
import com.talentica.copilot.exception.ResourceAlreadyExistsException;
import com.talentica.copilot.exception.ResourceNotFoundException;
import com.talentica.copilot.model.User;
import com.talentica.copilot.repository.UserRepository;
import java.time.ZoneOffset;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  //private final PasswordEncoder passwordEncoder;

  @Override
  public UserDto getUserById(Long id) {
    log.info("Getting user by id: {}", id);
    Optional<User> user = userRepository.findById(id);
    if(user.isEmpty()) {
      log.error("User not found with id: {}", id);
      throw new ResourceNotFoundException("User not found");
    }
    return UserDto.builder()
        .id(user.get().getId())
        .name(user.get().getName())
        .email(user.get().getEmail())
        .createdTime(user.get().getCreatedTime().toInstant(ZoneOffset.UTC))
        .updatedTime(user.get().getUpdatedTime().toInstant(ZoneOffset.UTC))
        .deleted(user.get().getDeleted())
        .verifiedAt(user.get().getVerifiedAt().toInstant(ZoneOffset.UTC))
        .deactivatedAt(user.get().getDeactivatedAt().toInstant(ZoneOffset.UTC))
        .build();
  }

  //create user if user with same email does not exist
  @Override
  public UserDto createUser(CreateUserRequest request) {
    log.info("Creating user with email: {}", request.getEmail());
    userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
      log.error("User with email already exists: {}", request.getEmail());
      throw new ResourceAlreadyExistsException("User with email already exists");
    });
    /*// encode the password
    String encodedPassword = passwordEncoder.encode(request.getPassword());
*/
    User user = User.builder()
        .name(request.getName())
        .email(request.getEmail())
        .password(request.getPassword())
        .build();
    User savedUser = userRepository.save(user);
    return UserDto.builder()
        .id(savedUser.getId())
        .name(savedUser.getName())
        .email(savedUser.getEmail())
        .deleted(savedUser.getDeleted())
        .verifiedAt(savedUser.getVerifiedAt().toInstant(ZoneOffset.UTC))
        .deactivatedAt(savedUser.getDeactivatedAt().toInstant(ZoneOffset.UTC))
        .build();
  }



}
