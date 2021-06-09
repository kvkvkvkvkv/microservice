package com.jpop.userservice.controller;

import com.jpop.userservice.dto.UserDto;
import com.jpop.userservice.model.User;
import com.jpop.userservice.service.UserMapper;
import com.jpop.userservice.service.UserServiceSpecification;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {

  @Inject UserServiceSpecification userServiceSpecification;

  @Inject UserMapper userMapper;

  @GetMapping("/")
  public ResponseEntity<List<UserDto>> getUser() {
    return ResponseEntity.ok(userServiceSpecification.getUser()
                                 .stream()
                                 .map(userMapper::convertToDto)
                                 .collect(Collectors.toList()));
  }

  @GetMapping("/{user_id}")
  public ResponseEntity<UserDto> getUser(@PathVariable("user_id") Long id) {
    return ResponseEntity.ok(
        userMapper.convertToDto(userServiceSpecification.getUser(id)));
  }

  @PostMapping("/")
  public ResponseEntity<UserDto> addUser(@RequestBody UserDto user) {
    User newUser =
        userServiceSpecification.saveUser(userMapper.convertToEntity(user));
    URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                       .path("{user_id}")
                       .buildAndExpand(newUser.getId())
                       .toUri();
    return ResponseEntity.created(location).body(
        userMapper.convertToDto(newUser));
  }

  @PutMapping("/{user_id}")
  public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user,
                                            @PathVariable("user_id") Long id) {
    User updateUser = userServiceSpecification.updateUser(
        userMapper.convertToEntity(user), id);
    return new ResponseEntity<>(userMapper.convertToDto(updateUser),
                                HttpStatus.OK);
  }

  @DeleteMapping("/{user_id}")
  public ResponseEntity<UserDto> deleteUser(@PathVariable("user_id") Long id) {
    userServiceSpecification.deleteUser(id);
    return new ResponseEntity<>(HttpStatus.GONE);
  }
}
