package com.tinkers.api.users.controllers;

import com.tinkers.api.users.service.IUserService;
import com.tinkers.core.beans.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UserController {
  private final IUserService userService;

  @Autowired
  public UserController(final IUserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public boolean create(@RequestBody final User user) {
    return userService.createUser(user);
  }

  @GetMapping
  public Collection<User> getAllUsers() {
    return userService.getUsers();
  }

  @GetMapping("/{id}")
  public User getUser(@PathVariable final int id) {
    return userService.getUser(id);
  }
}
