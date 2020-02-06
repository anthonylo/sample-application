package com.tinkers.core.dao.exceptions;

import java.io.Serializable;

public class EntityDoesNotExistException extends RuntimeException {
  /** Generated serialVersionUID. */
  private static final long serialVersionUID = -8786520123010552435L;

  private Class<? extends Serializable> idType;

  private Serializable id;

  public EntityDoesNotExistException() {}

  public EntityDoesNotExistException(final String message) {
    super(message);
  }

  public EntityDoesNotExistException(
          final String message, final Class<? extends Serializable> idType, final Serializable id) {
    super(message);
    this.idType = idType;
    this.id = id;
  }

  public Class<? extends Serializable> getIdType() {
    return idType;
  }

  public void setIdType(final Class<? extends Serializable> idType) {
    this.idType = idType;
  }

  public Serializable getId() {
    return id;
  }

  public void setId(final Serializable id) {
    this.id = id;
  }
}
