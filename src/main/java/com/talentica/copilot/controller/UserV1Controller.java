package com.talentica.copilot.controller;

import com.talentica.copilot.dto.CreateUserRequest;
import com.talentica.copilot.dto.UserDto;
import com.talentica.copilot.service.UserService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// V1 rest controller for the user
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserV1Controller {

  private final UserService userService;

  @GetMapping("/{id}")
  /*
   * Get user by id
   * @param id
   * @return UserDto
   * @throws ResourceNotFoundException
   */
  public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getUserById(id));
  }

  @PostMapping()
  /*
   * Create user
   * @param request
   * @return UserDto
   * @throws ResourceAlreadyExistsException
   */
  public ResponseEntity<UserDto> createUser(@RequestBody @Valid CreateUserRequest request) {
    UserDto userDto = userService.createUser(request);
    // create a response entity with the location header
    URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
        .buildAndExpand(userDto.getId()).toUri();
    return ResponseEntity.created(location).body(userDto);
  }

  // API to get user ratings
  @GetMapping("/{id}/ratings")
  public ResponseEntity<Long> getUserRatings(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getUserRatings(id));
  }
}
