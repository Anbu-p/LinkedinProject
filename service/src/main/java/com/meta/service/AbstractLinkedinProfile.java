package com.meta.service;

import com.meta.model.LinkedinProfile;

/**
 * <p>
 * Provides some abstract services for the user
 * </p>
 *
 * @author Anbu
 * @version 1.0
 */
public abstract class AbstractLinkedinProfile<T> implements LinkedinService<T> {

    /**
     * <p>
     * Signs in a new {@link LinkedinProfile}.
     * </p>
     *
     * @param linkedinProfile The {@link LinkedinProfile} to sign in.
     * @return True if the sign in was successful, false otherwise.
     */
    public abstract boolean signIn(final LinkedinProfile linkedinProfile);
}
