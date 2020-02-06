package com.tinkers.api.users.service;

import com.tinkers.core.beans.users.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
class UserService implements IUserService {
  private final AtomicInteger idGenerator;
  private final Map<Integer, User> users;

  public UserService() {
    users = new ConcurrentHashMap<>();
    idGenerator = new AtomicInteger(1);
  }

  @Override
  public boolean createUser(final User user) {
    user.setId(idGenerator.getAndAdd(1));
    users.put(user.getId(), user);
    return true;
  }

  @Override
  public Collection<User> getUsers() {
    return users.values();
  }

  @Override
  public User getUser(final int id) {
    return users.get(id);
  }
}
