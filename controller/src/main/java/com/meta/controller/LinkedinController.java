package com.meta.controller;

import com.meta.model.LinkedinProfile;
import com.meta.service.AbstractLinkedinProfile;
import com.meta.service.LinkedinService;
import com.meta.service.LinkedinServiceImpl;

/**
 * <p>
 * Handles user input and model state updates.
 * </p>
 *
 * @author Anbu
 * @version 1.0
 */
public class LinkedinController {

    private static final LinkedinService<LinkedinProfile> SERVICE = LinkedinServiceImpl.getServiceImplInstance();
    private static final AbstractLinkedinProfile<LinkedinProfile> ABSTRACT_LINKEDIN = LinkedinServiceImpl.getServiceImplInstance();
    private static LinkedinController linkedinController;

    private LinkedinController() {
    }

    /**
     * <p>
     * Gets the {@link LinkedinController} instance.
     * </p>
     *
     * @return linkedin controller instance.
     */
    public static LinkedinController getLinkedinControllerInstance() {
        if (linkedinController == null) {
            linkedinController = new LinkedinController();
        }

        return linkedinController;
    }

    /**
     * <p>
     * Delegates the signup operation to linkedin profile.
     * </p>
     *
     * @param userProfile The {@link LinkedinProfile} to be signed up.
     * @return True if the signup is successful, false otherwise.
     */
    public Long signUp(final LinkedinProfile userProfile) {
        return SERVICE.create(userProfile);
    }

    /**
     * <p>
     * Delegates the sign in operation to linkedin profile.
     * </p>
     *
     * @param userProfile The {@link LinkedinProfile} to be signed in.
     * @return True if the sign in is successful, false otherwise.
     */
    public boolean signIn(final LinkedinProfile userProfile) {
        return ABSTRACT_LINKEDIN.signIn(userProfile);
    }

    /**
     * <p>
     * Delegates the update operation to the linkedin profile.
     * </p>
     *
     * @param userProfile The {@link LinkedinProfile} to be updated.
     * @return True if the update is successful, false otherwise.
     */
    public boolean update(final LinkedinProfile userProfile) {
        return SERVICE.update(userProfile);
    }

    /**
     * <p>
     * Delegates the get operation to the linkedin profile.
     * </p>
     *
     * @param id The id of the profile to be retrieved.
     * @return The {@link LinkedinProfile} with the given id, or null if not found.
     */
    public LinkedinProfile get(final Long id) {
        return SERVICE.get(id);
    }

    /**
     * <p>
     * Delegates the delete operation to the linkedin profile.
     * </p>
     *
     * @param id The profile to be deleted.
     * @return True if delete is successful, false otherwise.
     */
    public boolean delete(final Long id) {
        return SERVICE.delete(id);
    }
}
