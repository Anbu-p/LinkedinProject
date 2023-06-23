package com.meta.controller;

import com.meta.model.Comment;
import com.meta.service.CommentServiceImpl;
import com.meta.service.LinkedinService;

import java.util.Collection;

/**
 * <p>
 * Handles user input and model state updates.
 * </p>
 *
 * @author Anbu
 * @version 1.0
 */
public class CommentController {

    private static final LinkedinService<Comment> SERVICE = CommentServiceImpl.getInstance();
    private static CommentController commentController;

    private CommentController() {
    }

    /**
     * <p>
     * Gets the {@link CommentController} instance.
     * </p>
     *
     * @return Comment controller instance.
     */
    public static CommentController getInstance() {
        if (commentController == null) {
            commentController = new CommentController();
        }

        return commentController;
    }

    /**
     * <p>
     * Delegates the create comment operation to the Linkedin post.
     * </p>
     *
     * @param comment The {@link Comment} to be added.
     * @return model.Comment id to the user
     */
    public Long create(final Comment comment) {
        return SERVICE.create(comment);
    }

    /**
     * <p>
     * Delegates the update comment operation to the linkedin post.
     * </p>
     *
     * @param comment The {@link Comment} to be updated.
     * @return True if the update is successful, false otherwise.
     */
    public boolean update(final Comment comment) {
        return SERVICE.update(comment);
    }

    /**
     * <p>
     * Delegates the delete comment operation to the linked in post.
     * </p>
     *
     * @param id The comment to be deleted.
     * @return True if delete is successful, false otherwise.
     */
    public boolean delete(final Long id) {
        return SERVICE.delete(id);
    }

    /**
     * <p>
     * Delegates the get comment operation to the linkedin post.
     * </p>
     *
     * @param postId The id of the comment to be retrieved.
     * @return The {@link Comment}  with the given id, or null if not found.
     */
    public Comment get(final Long postId) {
        return SERVICE.get(postId);
    }

    /**
     * <p>
     * Delegates the get all comment operation to the linkedin post.
     * </p>
     *
     * @param postId The id of the comments to be retrieved.
     * @return A {@link Collection} containing all comments.
     */
    public Collection<Comment> getAll(final Long postId) {
        return SERVICE.getAll(postId);
    }
}

