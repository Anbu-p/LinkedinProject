package com.meta.dao;

/**
 * <p>
 * Provides signin services for the user.
 * </p>
 *
 * @author Anbu
 * @version 1.0
 */
public abstract class AbstractLinkedinDao<T> implements DAO<T> {


    /**
     * <p>
     * Signs in a new {@link T}.
     * </p>
     *
     * @param linkedinProfile The {@link T} to sign in.
     * @return True if the sign in was successful, false otherwise.
     */
    public abstract boolean signIn(final T linkedinProfile);
}
