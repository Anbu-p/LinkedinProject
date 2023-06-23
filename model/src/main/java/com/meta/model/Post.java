package com.meta.model;

import com.meta.validation.Create;
import com.meta.validation.Delete;
import com.meta.validation.Get;
import com.meta.validation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * Communicates with the controller to update the data.
 * </p>
 *
 * @author Anbu
 * @version 1.0
 */
public class Post {
    @NotNull(groups = {Update.class, Delete.class, Get.class, Comment.class, Like.class}, message = "Please enter your post id")
    private Long id;

    @NotBlank(groups = {Create.class, Update.class}, message = "Please enter your post details")
    private String message;

    @Pattern(regexp = "^\\d+$", message = "Please enter valid profile id")
    private int postLikeCount;

    @Pattern(regexp = "^\\d+$", message = "Please enter valid profile id")
    private int commentCount;

    private LinkedinProfile linkedinProfile;

    public void setId(final Long id) {
        this.id = id;
    }

    public void setPostLikeCount(final int postLikeCount) {
        this.postLikeCount = postLikeCount;
    }

    public void setCommentCount(final int commentCount) {
        this.commentCount = commentCount;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setLinkedinProfile(final LinkedinProfile linkedinProfile) {
        this.linkedinProfile = linkedinProfile;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LinkedinProfile getLinkedinProfile() {
        return linkedinProfile;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getPostLikeCount() {
        return postLikeCount;
    }

    public String toString() {
        if (getCommentCount() == 0 && getPostLikeCount() == 0) {
            return String.format("Post id : %s%nPost Message : %s%n%s%n", id, message, linkedinProfile);
        } else if (getPostLikeCount() == 0 && getCommentCount() != 0) {
            return String.format("Post id : %s%nPost Message : %s%nComment Count :%s%n%s%n", id, message, commentCount, linkedinProfile);
        } else if (getPostLikeCount() != 0 && getCommentCount() == 0) {
            return String.format("Post id : %s%nPost Message : %s%nPost Like Count :%s%n%s%n", id, message, postLikeCount, linkedinProfile);
        } else {
            return String.format("Post id : %s%nPost Message : %s%nPost Like Count :%s%nComment Count :%s%n%s%n", id, message, postLikeCount, commentCount, linkedinProfile);
        }
    }
}
