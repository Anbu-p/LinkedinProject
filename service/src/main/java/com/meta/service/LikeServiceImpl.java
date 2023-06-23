package com.meta.service;

import com.meta.dao.AbstractLikeDao;
import com.meta.dao.DAO;
import com.meta.dao.LikeDaoImpl;
import com.meta.model.Like;

import java.util.Collection;

/**
 * <p>
 * Gets the implementation of the linkedin like service.
 * </p>
 *
 * @author Anbu
 * @version 1.0
 */
public class LikeServiceImpl extends AbstractLikeService<Like> {

    private final static DAO<Like> LIKE_DAO = LikeDaoImpl.getDaoImplInstance();
    private final static AbstractLikeDao<Like> ABSTRACT_LIKE_DAO = LikeDaoImpl.getDaoImplInstance();
    private static LikeServiceImpl likeService;
    private LikeServiceImpl() {
    }

    /**
     * <p>
     * Gets the {@link LikeServiceImpl} instance.
     * </p>
     *
     * @return model.Like service instance.
     */
    public static LikeServiceImpl getInstance() {
        if (likeService == null) {
            likeService = new LikeServiceImpl();
        }

        return likeService;
    }

    /**
     * {@inheritDoc}
     *
     * @param like The {@link Like} to update.
     * @return True if the like was updated successfully, false otherwise.
     */
    @Override
    public boolean update(final Like like) {
        return LIKE_DAO.update(like);
    }

    /**
     * {@inheritDoc}
     *
     * @param id The id of the like to get.
     * @return The {@link Like} with the specified id, or null if no such like exists.
     */
    @Override
    public Like get(final Long id) {
        return LIKE_DAO.get(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param id The id of the like to delete.
     * @return True if the like was deleted successfully, false otherwise.
     */
    @Override
    public boolean delete(final Long id) {
        return LIKE_DAO.delete(id);
    }

    @Override
    public Long create(final Like like) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param like The {@link Like} to create.
     * @return True if the like was created successfully, false otherwise.
     */
    @Override
    public boolean createLike(final Like like) {
        return ABSTRACT_LIKE_DAO.createLike(like);
    }

    /**
     * {@inheritDoc}
     *
     * @return A {@link Collection} of all likes, keyed by their id.
     */
    @Override
    public Collection<Like> getAll(final Long profileId) {
        return LIKE_DAO.getAll(profileId);
    }

    @Override
    public Collection<Like> getAll(Long commentId, Long postId) {
        return ABSTRACT_LIKE_DAO.getAll(commentId,postId);
    }
}
