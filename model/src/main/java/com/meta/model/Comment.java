package com.meta.model;

import com.meta.validation.Create;
import com.meta.validation.Delete;
import com.meta.validation.Get;
import com.meta.validation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * Communicates with the Controller to update the data.
 * </p>
 *
 * @author Anbu
 * @version 1.0
 */
public class Comment {
    @NotNull(groups = {Update.class, Delete.class, Get.class, Post.class}, message = "Please enter comment id")
    private Long id;
    @NotBlank(groups = {Create.class, Update.class}, message = "Please enter comment details")
    private String message;
   // @NotNull(groups = {Create.class, Update.class}, message = "Please enter your post id")
   // private Long postId;
   // @NotNull(groups = {Create.class, Update.class}, message = "Please enter your profile id")
   // private Long profileId;
    private int commentLikeCount;
    private Post post;

    public void setId(final Long id) {
        this.id = id;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setPost(final Post post) {
        this.post = post;
    }

    public void setCommentLikeCount(final int commentLikeCount) {
        this.commentLikeCount = commentLikeCount;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Post getPost() {
        return post;
    }

    public int getCommentLikeCount() {
        return commentLikeCount;
    }

    public String toString() {
        if (getCommentLikeCount() != 0) {
            return String.format("Comment id : %s%nComment message : %s%nComment like count : %s%n%s%n", id, message, commentLikeCount, post);
        } else {
            return String.format("Comment id : %s%nComment message : %s%n%s%n", id, message, post);
        }
    }
}
