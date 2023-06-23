package com.meta.service;

import com.meta.dao.DAO;
import com.meta.dao.PostDaoImpl;
import com.meta.model.Post;

import java.util.Collection;

/**
 * <p>
 * Gets the implementation of the AjioService interface.
 * </p>
 *
 * @author Anbu
 * @version 1.0
 */
public class PostServiceImpl implements LinkedinService<Post> {

    private final static DAO<Post> POST_DAO = PostDaoImpl.getDaoImplInstance();
    private static PostServiceImpl postService;
    private PostServiceImpl() {
    }

    /**
     * <p>
     * Gets the {@link PostServiceImpl} instance.
     * </p>
     *
     * @return model.Post service impl instance.
     */
    public static PostServiceImpl getInstance() {
        if (postService == null) {
            postService = new PostServiceImpl();
        }

        return postService;
    }

    /**
     * {@inheritDoc}
     *
     * @param post The {@link Post} to create.
     * @return True if the post was created successfully, false otherwise.
     */
    @Override
    public Long create(final Post post) {
        return POST_DAO.create(post);
    }

    /**
     * {@inheritDoc}
     *
     * @return A {@link Collection} of all posts, keyed by their id.
     */
    @Override
    public Collection<Post> getAll(final Long id) {
        return POST_DAO.getAll(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param post The {@link Post} to update.
     * @return True if the post was updated successfully, false otherwise.
     */
    @Override
    public boolean update(final Post post) {
        return POST_DAO.update(post);
    }

    /**
     * {@inheritDoc}
     *
     * @param id The id of the post to get.
     * @return The {@link Post} with the specified id, or null if no such post exists.
     */
    @Override
    public Post get(final Long id) {
        return POST_DAO.get(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param id The id of the post to delete.
     * @return True if the post was deleted successfully, false otherwise.
     */
    @Override
    public boolean delete(final Long id) {
        return POST_DAO.delete(id);
    }
}
