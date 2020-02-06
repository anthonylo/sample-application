package com.tinkers.core.beans;

import java.io.Serializable;

/**
 * This is a interface designating that the class which implements this bean will be usable by the
 * DAO framework.
 *
 * @param <ID> The ID type of the bean. The ID type can be a single key or a composite key.
 */
public interface Core<ID extends Serializable> extends Serializable {
  /**
   * Get the ID of this bean.
   *
   * @return The ID
   */
  ID getId();

  /**
   * The class type of the ID.
   *
   * @return A class type
   */
  Class<ID> getIdType();
}
