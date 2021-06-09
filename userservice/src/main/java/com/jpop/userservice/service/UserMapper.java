package com.jpop.userservice.service;

import com.jpop.userservice.dto.UserDto;
import com.jpop.userservice.model.User;
import javax.inject.Inject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

  ModelMapper modelMapper;

  @Inject
  public UserMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public UserDto convertToDto(User user) {
    return modelMapper.map(user, UserDto.class);
  }

  public User convertToEntity(UserDto userDto) {
    return modelMapper.map(userDto, User.class);
  }
}
