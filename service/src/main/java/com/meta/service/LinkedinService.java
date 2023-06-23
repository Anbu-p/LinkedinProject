package com.meta.service;

import java.util.Collection;

/**
 * <p>
 * Provides some services for the User.
 * </p>
 *
 * @author Anbu
 * @version 1.0
 */
public interface LinkedinService<T> {

    /**
     * <p>
     * Updates an existing {@link T}.
     * </p>
     *
     * @param type The {@link T} to update.
     * @return True if update successfully, false otherwise.
     */
    boolean update(final T type);

    /**
     * <p>
     * Gets the details by its id.
     * </p>
     *
     * @param id The corresponding id details to get.
     * @return The {@link T} with the specified id, or null if no such details exists.
     */
    T get(final Long id);

    /**
     * <p>
     * Deletes using its id.
     * </p>
     *
     * @param id The corresponding id details to be deleted.
     * @return True if deleted successfully, false otherwise.
     */
    boolean delete(final Long id);

    /**
     * <p>
     * Creates a new {@link T}.
     * </p>
     *
     * @param type The {@link T} to create.
     * @return It returns the id to the user.
     */
    Long create(final T type);

    /**
     * <p>
     * Gets all details.
     * </p>
     *
     * @param id The id of the details to be retrieved.
     * @return A {@link Collection} of all details, keyed by their id.
     */
    Collection<T> getAll(final Long id);
}
