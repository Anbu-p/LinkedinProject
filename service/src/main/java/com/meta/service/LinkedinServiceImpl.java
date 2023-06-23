package com.meta.service;

import com.meta.dao.AbstractLinkedinDao;
import com.meta.dao.DAO;
import com.meta.dao.DaoImpl;
import com.meta.model.LinkedinProfile;

import java.util.Collection;

/**
 * <p>
 * Gets the implementation of the Linkedin service.
 * </p>
 *
 * @author Anbu
 * @version 1.0
 */
public class LinkedinServiceImpl extends AbstractLinkedinProfile<LinkedinProfile> {

    private static LinkedinServiceImpl serviceImpl;
    private final static DAO<LinkedinProfile> DAO_IMPL = DaoImpl.getDaoImplInstance();
    private final static AbstractLinkedinDao<LinkedinProfile> LINKEDIN_DAO = DaoImpl.getDaoImplInstance();

    private LinkedinServiceImpl() {
    }

    /**
     * <p>
     * Gets the {@link LinkedinServiceImpl} instance.
     * </p>
     *
     * @return LinkedIn service Impl instance.
     */
    public static LinkedinServiceImpl getServiceImplInstance() {
        if (serviceImpl == null) {
            serviceImpl = new LinkedinServiceImpl();
        }

        return serviceImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param linkedinProfile The {@link LinkedinProfile} to sign in.
     * @return True if the sign in was successful, false otherwise.
     */
    @Override
    public boolean signIn(final LinkedinProfile linkedinProfile) {
        return LINKEDIN_DAO.signIn(linkedinProfile);
    }

    /**
     * {@inheritDoc}
     *
     * @param linkedinProfile The {@link LinkedinProfile} to update.
     * @return True if the profile was updated successfully, false otherwise.
     */
    @Override
    public boolean update(final LinkedinProfile linkedinProfile) {
        return DAO_IMPL.update(linkedinProfile);
    }

    /**
     * {@inheritDoc}
     *
     * @param id The id of the profile to get.
     * @return The {@link LinkedinProfile} with the specified id, or null if no such profile exists.
     */
    @Override
    public LinkedinProfile get(final Long id) {
        return LINKEDIN_DAO.get(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param id The id of the product to delete.
     * @return True if the profile was deleted successfully, false otherwise.
     */
    @Override
    public boolean delete(final Long id) {
        return DAO_IMPL.delete(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param linkedinProfile The {@link LinkedinProfile} to create.
     * @return The id of the profile.
     */
    @Override
    public Long create(final LinkedinProfile linkedinProfile) {
        return DAO_IMPL.create(linkedinProfile);
    }

    /**
     * {@inheritDoc}
     *
     * @return A {@link Collection} of all profiles, keyed by their id.
     */
    @Override
    public Collection<LinkedinProfile> getAll(final Long profileId) {
        return null;
    }
}
