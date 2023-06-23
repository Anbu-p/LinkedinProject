package com.meta.controller;

import com.meta.model.Like;
import com.meta.service.AbstractLikeService;
import com.meta.service.LikeServiceImpl;
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
public class LikeController {

    private static final LinkedinService<Like> SERVICE = LikeServiceImpl.getInstance();
    private static final AbstractLikeService<Like> LIKE_SERVICE = LikeServiceImpl.getInstance();
    private static LikeController likeController;

    private LikeController() {
    }

    /**
     * <p>
     * Gets the {@link LikeController} instance.
     * </p>
     *
     * @return like controller instance.
     */
    public static LikeController getInstance() {
        if (likeController == null) {
            likeController = new LikeController();
        }

        return likeController;
    }

    /**
     * <p>
     * Delegates the create like operation to the Linkedin post.
     * </p>
     *
     * @param like The {@link Like} to be added.
     * @return model.Like id to the user
     */
    public boolean createLike(final Like like) {
        return LIKE_SERVICE.createLike(like);
    }

    /**
     * <p>
     * Delegates the get all like operation to the linkedin profile.
     * </p>
     *
     * @param id The id of the likes to be retrieved.
     * @return A {@link Collection} containing all likes.
     */
    public Collection<Like> getAll(final Long id) {
        return SERVICE.getAll(id);
    }

    /**
     * @param commentId The comment id of the likes to be retrieved.
     * @param postId    The post id of the likes to be retrieved.
     * @return
     */
    public Collection<Like> getAll(final Long commentId, final Long postId) {
        return LIKE_SERVICE.getAll(commentId, postId);
    }
}


