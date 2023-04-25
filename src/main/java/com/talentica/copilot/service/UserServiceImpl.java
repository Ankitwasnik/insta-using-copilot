package com.talentica.copilot.service;

import com.talentica.copilot.dto.CreateUserRequest;
import com.talentica.copilot.dto.ExtendedUserDetails;
import com.talentica.copilot.dto.UserDto;
import com.talentica.copilot.exception.ResourceAlreadyExistsException;
import com.talentica.copilot.exception.ResourceNotFoundException;
import com.talentica.copilot.model.User;
import com.talentica.copilot.repository.UserRepository;
import com.talentica.copilot.util.DateTimeUtil;
import com.talentica.copilot.util.SecurityUtil;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDto getUserById(Long id) {
    log.info("Getting user by id: {}", id);
    User user = userRepository.findById(id).orElseThrow(() -> {
      log.error("User not found with id: {}", id);
      return new ResourceNotFoundException("User not found");
    });

    return UserDto.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .createdTime(user.getCreatedTime().toInstant(ZoneOffset.UTC))
        .updatedTime(user.getUpdatedTime().toInstant(ZoneOffset.UTC))
        .deleted(user.isDeleted())
        .verifiedAt(DateTimeUtil.convertToInstant(user.getVerifiedAt()))
        .deactivatedAt(DateTimeUtil.convertToInstant(user.getDeactivatedAt()))
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
    String encodedPassword = passwordEncoder.encode(request.getPassword());

    User user = User.builder()
        .name(request.getName())
        .email(request.getEmail())
        .password(encodedPassword)
        .isActive(true)
        .build();
    User savedUser = userRepository.save(user);
    return UserDto.builder()
        .id(savedUser.getId())
        .name(savedUser.getName())
        .email(savedUser.getEmail())
        .deleted(savedUser.isDeleted())
        .verifiedAt(DateTimeUtil.convertToInstant(savedUser.getVerifiedAt()))
        .deactivatedAt(DateTimeUtil.convertToInstant(savedUser.getDeactivatedAt()))
        .build();
  }

  @Override
  public User getLoggedInUser() {
    log.info("Getting logged in user");
    Long userId = SecurityUtil.getUserId();
    return userRepository.findById(userId).orElseThrow(() -> {
      log.error("User not found with id: {}", userId);
      return new ResourceNotFoundException("User not found");
    });
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("Loading user by username: {}", username);
    User user = userRepository.findByEmail(username).orElseThrow(() -> {
      log.error("User not found with email: {}", username);
      return new UsernameNotFoundException("User not found");
    });

    return new ExtendedUserDetails(user.getId(), user.getEmail(), user.getPassword(),
        user.isActive(), List.of(new SimpleGrantedAuthority("USER")));
  }


}
