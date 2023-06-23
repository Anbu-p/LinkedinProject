package com.meta.controller;

import com.meta.model.Post;
import com.meta.service.LinkedinService;
import com.meta.service.PostServiceImpl;

import java.util.Collection;

/**
 * <p>
 * Handles user input and model state updates.
 * </p>
 *
 * @author Anbu
 * @version 1.0
 */
public class PostController {

    private static final LinkedinService<Post> SERVICE = PostServiceImpl.getInstance();
    private static PostController postController;

    private PostController() {
    }

    /**
     * <p>
     * Gets the {@link PostController} instance.
     * </p>
     *
     * @return model.Post controller instance.
     */
    public static PostController getInstance() {
        if (postController == null) {
            postController = new PostController();
        }

        return postController;
    }

    /**
     * <p>
     * Delegates the create post operation to the Linkedin profile.
     * </p>
     *
     * @param post The {@link Post} to be added.
     * @return model.Post id to the user
     */
    public Long create(final Post post) {
        return SERVICE.create(post);
    }

    /**
     * <p>
     * Delegates the update post operation to the linkedin post.
     * </p>
     *
     * @param post The {@link Post} to be updated.
     * @return True if the update is successful, false otherwise.
     */
    public boolean update(final Post post) {
        return SERVICE.update(post);
    }

    /**
     * <p>
     * Delegates the delete post operation to the linked in post.
     * </p>
     *
     * @param id The post to be deleted.
     * @return True if delete is successful, false otherwise.
     */
    public boolean delete(final Long id) {
        return SERVICE.delete(id);
    }

    /**
     * <p>
     * Delegates the get post operation to the linkedin post.
     * </p>
     *
     * @param id The id of the post to be retrieved.
     * @return The {@link Post} with the given id, or null if not found.
     */
    public Post get(final Long id) {
        return SERVICE.get(id);
    }

    /**
     * <p>
     * Delegates the get all post operation to the linkedin profile.
     * </p>
     *
     * @param id The id of the posts to be retrieved.
     * @return A {@link Collection} containing all posts.
     */
    public Collection<Post> getAll(final Long id) {
        return SERVICE.getAll(id);
    }
}
