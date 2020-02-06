package com.tinkers.api.users.service;

import com.tinkers.core.beans.users.User;

import java.util.Collection;

public interface IUserService {
  boolean createUser(final User user);

  Collection<User> getUsers();

  User getUser(final int id);
}
