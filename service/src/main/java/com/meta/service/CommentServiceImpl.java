package com.meta.service;

import com.meta.dao.CommentDaoImpl;
import com.meta.dao.DAO;
import com.meta.model.Comment;

import java.util.Collection;

/**
 * <p>
 * Gets the implementation of the linkedin comment service.
 * </p>
 *
 * @author Anbu
 * @version 1.0
 */
public class CommentServiceImpl implements LinkedinService<Comment> {

    private final static DAO<Comment> COMMENT_DAO = CommentDaoImpl.getDaoImplInstance();
    private static CommentServiceImpl commentService;
    private CommentServiceImpl() {
    }

    /**
     * <p>
     * Gets the {@link CommentServiceImpl} instance.
     * </p>
     *
     * @return comment service impl instance.
     */
    public static CommentServiceImpl getInstance() {
        if (commentService == null) {
            commentService = new CommentServiceImpl();
        }

        return commentService;
    }

    /**
     * {@inheritDoc}
     *
     * @param comment The {@link Comment} to create.
     * @return True if the comment was created successfully, false otherwise.
     */
    @Override
    public Long create(final Comment comment) {
        return COMMENT_DAO.create(comment);
    }

    /**
     * {@inheritDoc}
     *
     * @return A {@link Collection} of all comments, keyed by their id.
     */
    @Override
    public Collection<Comment> getAll(final Long id) {
        return COMMENT_DAO.getAll(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param comment The {@link Comment} to update.
     * @return True if the comment was updated successfully, false otherwise.
     */
    @Override
    public boolean update(final Comment comment) {
        return COMMENT_DAO.update(comment);
    }

    /**
     * {@inheritDoc}
     *
     * @param id The id of the comment to get.
     * @return The {@link Comment} with the specified id, or null if no such comment exists.
     */
    @Override
    public Comment get(final Long id) {
        return COMMENT_DAO.get(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param id The id of the comment to be deleted.
     * @return True if the comment was deleted successfully, false otherwise.
     */
    @Override
    public boolean delete(final Long id) {
        return COMMENT_DAO.delete(id);
    }
}

