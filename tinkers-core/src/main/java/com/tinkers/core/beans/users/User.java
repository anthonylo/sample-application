package com.tinkers.core.beans.users;

import com.tinkers.core.beans.Core;

import java.util.Objects;

public class User implements Core<Integer> {
  private Integer id;
  private String firstName;
  private String lastName;
  private long age;

  @Override
  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public long getAge() {
    return age;
  }

  public void setAge(final long age) {
    this.age = age;
  }

  @Override
  public Class<Integer> getIdType() {
    return Integer.class;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final User user = (User) o;
    return id == user.id
        && age == user.age
        && Objects.equals(firstName, user.firstName)
        && Objects.equals(lastName, user.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, age);
  }

  @Override
  public String toString() {
    return "User{"
        + "id="
        + id
        + ", firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", age="
        + age
        + '}';
  }
}
