package com.tinkers.core.dao;

import java.util.List;
import java.util.Set;

public interface IRepository<T, K> {
  /**
   * Saves an entity to the database.
   *
   * @param entity - The entity to be persisted to the database.
   */
  void saveEntity(T entity);

  /**
   * Retrieves a set of Entities of a certain class by ID.
   *
   * @param id - ID to query for.
   * @return A set of Entities of a certain class.
   */
  Set<T> retrieveById(K id);

  /**
   * Retrieves a unique entity by ID.
   *
   * @param id - ID to query for.
   * @return A single of entity of a certain class.
   */
  T retrieveUniqueById(K id);

  /**
   * Retrieve everything of a certain type.
   *
   * @return A list of a type.
   */
  List<T> retrieveAll();

  /**
   * Retrieves a specific subset of the entity as specified by the user.
   *
   * @param startIdx - The initial index to start at.
   * @param count - The amount of rows to return.
   * @return A list representing the subset query.
   */
  List<T> retrieveSubsetOfEndpoint(int startIdx, int count);

  /**
   * Checks if an entity with a certain ID exists in the database.
   *
   * @param id - ID to query for.
   * @return true or false depending if the entity exists.
   */
  boolean doesEntityExistById(K id);

  /**
   * Determine if the table is empty.
   *
   * @return true or false if the table is empty.
   */
  boolean tableEmpty();

  /**
   * Get the count of the table.
   *
   * @return A {@link Long} representing the number of rows in the table.
   */
  Long retrieveCount();

  /**
   * Updates an entity that should already exist. If it doesn't exist, insert it.
   *
   * @param entity - Entity to be persisted.
   */
  void updateEntity(T entity);

  /**
   * Delete an entity by ID.
   *
   * @param id - ID to query for.
   */
  void deleteEntityById(K id);
}
