package com.meta.model;

import com.meta.validation.Create;
import com.meta.validation.Delete;
import com.meta.validation.Get;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * <p>
 * Communicates with the Controller to update the data.
 * </p>
 *
 * @author Anbu
 * @version 1.0
 */
public class Like {
    @NotNull(groups = {Get.class}, message = "Please enter like id")
    private Long id;
   /* @NotNull(groups = {Create.class, Delete.class, Get.class}, message = "Please enter valid post id")
    private Long postId;*/
   // private Long commentId;
    /*@NotNull(groups = {Create.class}, message = "Please enter valid details")
    private Long profileId;*/
    @NotNull(groups = {Create.class}, message = "Please enter your reaction")
    private Reaction reaction;
    private boolean isLike;
    private Post post;
    private Comment comment;

    public void setId(final Long id) {
        this.id = id;
    }

    public void setPost(final Post post) {
        this.post = post;
    }

    public void setComment(final Comment comment) {
        this.comment = comment;
    }

    public void setLike(final boolean isLike) {
        this.isLike = isLike;
    }

    public void setReaction(final Reaction reaction) {
        this.reaction = reaction;
    }

    public Long getId() {
        return id;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public Post getPost() {
        return post;
    }

    public Comment getComment() {
        return comment;
    }

    public boolean getLike() {
        return isLike;
    }
}
