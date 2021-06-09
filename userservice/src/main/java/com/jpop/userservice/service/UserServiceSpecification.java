package com.jpop.userservice.service;

import com.jpop.userservice.model.User;
import java.util.List;

public interface UserServiceSpecification {

  List<User> getUser();

  User getUser(Long id);

  User saveUser(User user);

  User updateUser(User user, Long id);

  void deleteUser(Long id);
}
