package com.jpop.userservice.service;

import com.jpop.userservice.exception.UserNotFoundException;
import com.jpop.userservice.model.User;
import com.jpop.userservice.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserServiceSpecification {

  UserRepository userRepository;

  @Inject
  public UserServiceImplementation(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<User> getUser() {
    return (List<User>)userRepository.findAll();
  }

  @Override
  public User getUser(Long id) {
    return userRepository.findById(id).orElseThrow(
        () -> new UserNotFoundException("User is unavailable!"));
  }

  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public User updateUser(User user, Long id) {

    Optional<User> updateUser = userRepository.findById(id);
    if (updateUser.isPresent()) {
      User update = updateUser.get();
      update.setName(user.getName());
      update.setAddress(user.getAddress());
      return update;
    }
    user.setId(id);
    return saveUser(user);
  }

  @Override
  public void deleteUser(Long id) {
    if (getUser(id) != null) {
      userRepository.deleteById(id);
    }
  }
}
